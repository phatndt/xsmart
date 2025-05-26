package my.phatndt.xsmart.android.features.dashboard.state

import my.phatndt.xsmart.android.core.base.UiState
import my.phatndt.xsmart.android.features.dashboard.model.FeatureItem

data class DashboardUiState(
    val features: List<FeatureItem> = emptyList(),
) : UiState
