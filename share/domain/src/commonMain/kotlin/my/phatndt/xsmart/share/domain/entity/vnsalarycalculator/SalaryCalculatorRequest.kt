package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

data class SalaryCalculatorRequest(
    val salary: KmmBigDecimal,
    val insuranceSalary: KmmBigDecimal,
    val area: Area,
    val dependents: Int,
    val allowances: KmmBigDecimal,
    val allowanceType: AllowanceType,
    val calculatorMode: CalculatorMode,
)
