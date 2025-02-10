package my.phatndt.xsmart.android.features.dashboard

import androidx.compose.ui.graphics.vector.ImageVector
import my.phatndt.xsmart.android.core.base.UiState

data class DashboardUiState(
    val features: List<FeatureItem> = emptyList(),
) : UiState

data class FeatureItem(
    val name: String,
    val icon: ImageVector,
    val type: FeatureType,
)

enum class FeatureType {
    VN_SALARY_CALCULATOR,
    BMI_CALCULATOR,
}
