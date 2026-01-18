package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.amount.ZERO
import my.phatndt.xsmart.share.common.amount.minus
import my.phatndt.xsmart.share.common.amount.plus
import my.phatndt.xsmart.share.common.amount.times
import my.phatndt.xsmart.share.common.amount.toKmmBigDecimal
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceType
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.DeductionEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.SalaryCalculatorRequest
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxBracket
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxInfoEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryCalculatorConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VietnamSalaryConfig

class CalculateVnSalaryUseCaseImpl : CalculateVnSalaryUseCase {
    override fun invoke(request: SalaryCalculatorRequest): Flow<DataResult<VnSalaryCalculatorEntity>> = flow {
        emit(
            DataResult.Success(calculateNetSalary(request)),
        )
    }

    private fun calculateNetSalary(request: SalaryCalculatorRequest): VnSalaryCalculatorEntity {
        val grossSalary = request.salary
        val config = request.config

        // Insurance
        val insurance = VnSalaryCalculatorInsuranceEntity(
            socialInsurance = request.insuranceSalary * config.socialInsuranceRate,
            healthInsurance = request.insuranceSalary * config.healthInsuranceRate,
            unemploymentInsurance = request.insuranceSalary * config.unemploymentInsuranceRate,
        )

        // Deduction
        val deduction = DeductionEntity(
            personal = config.personalDeduction,
            dependent = config.dependentDeduction * KmmBigDecimal(request.dependents),
        )

        // Tax
        val beforeTaxIncome = grossSalary - insurance.totalInsurance
        val allowanceTax = if (request.allowanceType == AllowanceType.INCLUDED) {
            request.allowances
        } else {
            ZERO
        }
        val taxableIncome = beforeTaxIncome - deduction.totalDeduction - allowanceTax
        val taxBrackets = calculateTax(taxableIncome, config.taxBrackets)
        val taxInfo = TaxInfoEntity(
            beforeTaxIncome = beforeTaxIncome,
            taxableIncome =taxableIncome,
            totalTax = taxBrackets.first,
            taxBrackets = taxBrackets.second,
        )

        // Net salary
        val netSalary = grossSalary - insurance.totalInsurance - taxBrackets.first

        return VnSalaryCalculatorEntity(
            grossSalary = grossSalary,
            netSalary = netSalary,
            insurance = insurance,
            deduction = deduction,
            taxInfo = taxInfo,
            calculatorMode = CalculatorMode.GROSS_TO_NET,
            allowance = request.allowances,
            dependents = request.dependents,
            config = config,
        )
    }

    private fun calculateTax(
        taxableIncome: KmmBigDecimal,
        taxBrackets: List<TaxBracket>,
    ): Pair<KmmBigDecimal, List<Pair<KmmBigDecimal, TaxBracket>>> {
        var remainingAmount = taxableIncome
        var totalTax = ZERO
        val breakdowns = mutableListOf<Pair<KmmBigDecimal, TaxBracket>>()

        for (bracket in taxBrackets) {
            if (remainingAmount <= ZERO) {
                breakdowns += ZERO to bracket
                break
            }

            // no upper bound → unlimited
            val bracketCapacity = bracket.upperBound?.minus(bracket.lowerBound) ?: remainingAmount

            val taxableInBracket = minOf(remainingAmount, bracketCapacity)

            val taxForBracket = taxableInBracket * bracket.rate.toKmmBigDecimal()
            totalTax += taxForBracket
            remainingAmount -= taxableInBracket
            breakdowns += taxForBracket to bracket
        }

        return totalTax to breakdowns
    }
}
