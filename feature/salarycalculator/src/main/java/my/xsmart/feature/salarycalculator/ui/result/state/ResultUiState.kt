package my.xsmart.feature.salarycalculator.ui.result.state

import androidx.compose.ui.graphics.Color
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.deferred.DeferredColor
import my.phatndt.xsmart.share.common.deferred.DeferredText
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity

data class ResultUiState(
    val calculationData: VnSalaryCalculatorEntity? = null,
    val isLoading: Boolean = false,
    val salaryBreakdownItems: List<SalaryBreakdownUiState> = emptyList(),
//    val grossSalary: KmmBigDecimal,
//    val netSalary: KmmBigDecimal,
//    val insurance: VnSalaryCalculatorInsuranceEntity,
//    val personalDeduction: KmmBigDecimal,
//    val dependentDeduction: KmmBigDecimal,
//    val beforeTaxIncome: KmmBigDecimal,
//    val tax: KmmBigDecimal,
//    val taxableIncome: KmmBigDecimal,
//    val calculatorMode: CalculatorMode,
//    val allowance: KmmBigDecimal,
//    val bonus: KmmBigDecimal,
)

data class SalaryBreakdownUiState(
    val title: DeferredText,
    val amount: DeferredText,
    val color: Color,
    val percent: Float,
)
