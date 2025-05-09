package my.phatndt.xsmart.model.entity.vnsalarycalculator

import my.phatndt.xsmart.share.KmmBigDecimal
import my.phatndt.xsmart.share.plus

data class VnSalaryCalculatorEntity(
    val grossSalary: KmmBigDecimal,
    val netSalary: KmmBigDecimal,
    val insurance: VnSalaryCalculatorInsuranceEntity,
    val personalDeduction: KmmBigDecimal,
    val dependentDeduction: KmmBigDecimal,
    val beforeTaxIncome: KmmBigDecimal,
    val tax: KmmBigDecimal,
    val taxableIncome: KmmBigDecimal,
    val calculatorMode: CalculatorMode,
    val allowance: KmmBigDecimal,
    val bonus: KmmBigDecimal,
)

data class VnSalaryCalculatorInsuranceEntity(
    val socialInsurance: KmmBigDecimal,
    val healthInsurance: KmmBigDecimal,
    val unemploymentInsurance: KmmBigDecimal,
) {
    val totalInsurance: KmmBigDecimal
        get() = socialInsurance + healthInsurance + unemploymentInsurance
}
