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
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceEntity
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
        val grossSalary = request.salary + if (request.allowanceType == AllowanceType.SEPARATED) {
            request.allowances
        } else {
            ZERO
        }
        
        // Add additional income to gross salary
        val totalGrossSalary = grossSalary + request.additionalIncome
        
        val config = request.config

        // Insurance
        // Insurance logic
        val socialHealthCap = config.baseSalary * KmmBigDecimal(20.0)
        val unemploymentCap = (config.regionalMinimumWage[request.area] ?: ZERO) * KmmBigDecimal(20.0)

        val socialHealthSalary = minOf(request.insuranceSalary, socialHealthCap)
        val unemploymentSalary = minOf(request.insuranceSalary, unemploymentCap)

        val insurance = VnSalaryCalculatorInsuranceEntity(
            socialInsurance = socialHealthSalary * config.socialInsuranceRate,
            healthInsurance = socialHealthSalary * config.healthInsuranceRate,
            unemploymentInsurance = unemploymentSalary * config.unemploymentInsuranceRate,
        )

        // Deduction
        val deduction = DeductionEntity(
            personal = config.personalDeduction,
            dependent = config.dependentDeduction * KmmBigDecimal(request.dependents),
        )

        // Union fee (1% of gross salary when enabled)
        val unionFee = if (request.unionFeeEnabled) {
            socialHealthSalary * config.unionFeeRate.toKmmBigDecimal()
        } else {
            ZERO
        }

        // Tax
        val beforeTaxIncome = totalGrossSalary - insurance.totalInsurance - request.allowances

        val taxableIncome = maxOf(beforeTaxIncome - deduction.totalDeduction, ZERO)
        val taxBrackets = calculateTax(taxableIncome, config.taxBrackets)
        val taxInfo = TaxInfoEntity(
            beforeTaxIncome = beforeTaxIncome,
            taxableIncome =taxableIncome,
            totalTax = taxBrackets.first,
            taxBrackets = taxBrackets.second,
        )

        // Net salary (deduct union fee from final calculation)
        val netSalary = totalGrossSalary - insurance.totalInsurance - taxBrackets.first - unionFee
        // Allowance
        val allowance = AllowanceEntity(
            allowance = request.allowances,
            allowanceType = request.allowanceType,
        )

        return VnSalaryCalculatorEntity(
            grossSalary = grossSalary,
            netSalary = netSalary,
            insurance = insurance,
            deduction = deduction,
            taxInfo = taxInfo,
            calculatorMode = CalculatorMode.GROSS_TO_NET,
            allowance = allowance,
            dependents = request.dependents,
            config = config,
            unionFee = unionFee,
            additionalIncome = request.additionalIncome,
        )
    }

    private fun calculateTax(
        taxableIncome: KmmBigDecimal,
        taxBrackets: List<TaxBracket>,
    ): Pair<KmmBigDecimal, List<Pair<KmmBigDecimal, TaxBracket>>> {
        if (taxableIncome == ZERO) return ZERO to emptyList()
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
