package my.xsmart.feature.salarycalculator.ui.result.state

import androidx.compose.ui.graphics.Color
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.deferred.DeferredText
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegment
import my.xsmart.share.android.base.UiState
import java.math.BigDecimal

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

data class DetailedCalculationUiState(
    val data: VnSalaryCalculatorEntity? = null,
    val taxBrackets: List<TaxBracketModel> = emptyList(),
    val socialInsuranceRate: String = "",
    val healthInsuranceRate: String = "",
    val unemploymentInsuranceRate: String = "",
)

data class TaxBracketModel(
    val percent: Int,
    val min: BigDecimal?,
    val max: BigDecimal?,
    val amount: BigDecimal,
    val isActive: Boolean
)
