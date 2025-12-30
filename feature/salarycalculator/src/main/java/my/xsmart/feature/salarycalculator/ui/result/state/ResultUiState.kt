package my.xsmart.feature.salarycalculator.ui.result.state

import androidx.compose.ui.graphics.Color
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.deferred.DeferredColor
import my.phatndt.xsmart.share.common.deferred.DeferredText
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity
import my.xsmart.feature.salarycalculator.component.DetailedCalculationUiState
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegment
import my.xsmart.share.android.base.UiState

data class ResultUiState(
    val calculationData: VnSalaryCalculatorEntity? = null,
    val isLoading: Boolean = false,
    val salaryBreakdownItems: List<BreakdownSegment> = emptyList(),
    val detailedCalculationUiState: DetailedCalculationUiState = DetailedCalculationUiState(),

): UiState

data class SalaryBreakdownUiState(
    val title: DeferredText,
    val amount: DeferredText,
    val color: Color,
    val percent: Double,
)
