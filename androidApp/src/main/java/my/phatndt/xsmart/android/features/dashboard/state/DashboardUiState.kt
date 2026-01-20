package my.phatndt.xsmart.android.features.dashboard.state

import my.phatndt.xsmart.android.features.dashboard.model.FeatureItem
import my.xsmart.share.android.base.UiState

data class DashboardUiState(
    val features: List<FeatureItem> = emptyList(),
) : UiState
