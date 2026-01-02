package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.amount.plus

data class VnSalaryCalculatorEntity(
    val grossSalary: KmmBigDecimal,
    val netSalary: KmmBigDecimal,
    val insurance: VnSalaryCalculatorInsuranceEntity,
    val deduction: DeductionEntity,
    val taxInfo: TaxInfoEntity,
    val calculatorMode: CalculatorMode,
    val allowance: KmmBigDecimal,
    val dependents: Int = 0,
    val config: VnSalaryCalculatorConfig = VnSalaryCalculatorConfig(),
)

data class VnSalaryCalculatorInsuranceEntity(
    val socialInsurance: KmmBigDecimal,
    val healthInsurance: KmmBigDecimal,
    val unemploymentInsurance: KmmBigDecimal,
) {
    val totalInsurance: KmmBigDecimal
        get() = socialInsurance + healthInsurance + unemploymentInsurance
}

data class DeductionEntity(
    val personal: KmmBigDecimal,
    val dependent: KmmBigDecimal,
) {
    val totalDeduction: KmmBigDecimal
        get() = personal + dependent
}

data class TaxInfoEntity(
    val beforeTaxIncome: KmmBigDecimal,
    val taxableIncome: KmmBigDecimal,
    val totalTax: KmmBigDecimal,
    val taxBrackets: List<Pair<KmmBigDecimal, TaxBracket>>,
)
