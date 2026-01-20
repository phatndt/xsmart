package my.xsmart.feature.salarycalculator.ui.result.model

import androidx.compose.ui.graphics.Color
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

enum class BreakdownSegmentType {
    NET_SALARY,
    INSURANCE,
    TAX,
}
data class BreakdownSegment(
    val type : BreakdownSegmentType,
    val percentage: Double,
    val amount: KmmBigDecimal,
)
