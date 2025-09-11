package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.amount.minus
import my.phatndt.xsmart.share.common.amount.plus
import my.phatndt.xsmart.share.common.amount.times
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxBracket
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity

class CalculateVnSalaryUseCaseImpl : CalculateVnSalaryUseCase {
    override fun invoke(
        salary: KmmBigDecimal,
        insuranceSalary: KmmBigDecimal,
        area: Area,
        numberOfDependents: Int,
        calculatorMode: CalculatorMode,
    ): Flow<DataResult<VnSalaryCalculatorEntity>> = flow {
        emit(
            DataResult.Success(
                calculateNetSalary(
                    salary,
                    insuranceSalary,
                    numberOfDependents,
                    VnSalaryCalculatorConfig(),
                ),
            ),
        )
    }

    private fun calculateNetSalary(
        grossSalary: KmmBigDecimal,
        insuranceSalary: KmmBigDecimal,
        numberOfDependents: Int,
        config: VnSalaryCalculatorConfig,
    ): VnSalaryCalculatorEntity {
        val socialInsurance = insuranceSalary * config.socialInsuranceRate
        val healthInsurance = insuranceSalary * config.healthInsuranceRate
        val unemploymentInsurance = insuranceSalary * config.unemploymentInsuranceRate
        val totalInsurance = socialInsurance + healthInsurance + unemploymentInsurance

        val beforeTaxIncome = grossSalary - totalInsurance

        var dependentDeduction = KmmBigDecimal(0)
        for (i in 1..numberOfDependents) {
            dependentDeduction += config.dependentDeduction
        }
        val taxableIncome = beforeTaxIncome - config.personalDeduction - dependentDeduction

        val tax = calculateTax(taxableIncome, config.taxBrackets)

        val netSalary = grossSalary - totalInsurance - tax

        return VnSalaryCalculatorEntity(
            grossSalary = grossSalary,
            netSalary = netSalary,
            insurance = VnSalaryCalculatorInsuranceEntity(
                socialInsurance = socialInsurance,
                healthInsurance = healthInsurance,
                unemploymentInsurance = unemploymentInsurance,
            ),
            personalDeduction = config.personalDeduction,
            dependentDeduction = dependentDeduction,
            beforeTaxIncome = beforeTaxIncome,
            tax = tax,
            taxableIncome = taxableIncome,
            calculatorMode = CalculatorMode.GROSS_TO_NET,
            allowance = KmmBigDecimal(0),
            bonus = KmmBigDecimal(0),
        )
    }

    private fun calculateTax(
        taxableIncome: KmmBigDecimal,
        taxBrackets: List<TaxBracket>,
    ): KmmBigDecimal {
        var tax = KmmBigDecimal(0)
        var temp = KmmBigDecimal(0)

        for (bracket in taxBrackets) {
            if (temp > taxableIncome) break

            val taxableInBracket =
                if (taxableIncome - temp > (bracket.upperBound?.minus(bracket.lowerBound) ?: KmmBigDecimal(0))
                ) {
                    bracket.upperBound?.minus(bracket.lowerBound)
                        ?: (taxableIncome - temp)
                } else {
                    taxableIncome - temp
                }
            if (taxableInBracket > KmmBigDecimal(0)) {
                tax += (taxableInBracket * bracket.rate)
                temp += taxableInBracket
            }
        }

        return tax
    }
}
