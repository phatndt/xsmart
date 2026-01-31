package my.xsmart.feature.dashboard.ui.main.state

import my.xsmart.share.android.base.UiState

data class DashboardUiState(
    val userName: String = "Nguyen Van A",
    val hasNotifications: Boolean = true,
    val selectedBottomNavItem: Int = 0,
): UiState
