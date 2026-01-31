package my.xsmart.feature.dashboard.ui.main.state

import my.xsmart.share.android.base.UiIntent

sealed interface DashboardUiIntent : UiIntent {
    data object OnStartCalculatingClicked : DashboardUiIntent
    data object OnSalaryCalcClicked : DashboardUiIntent
    data object OnTaxClicked : DashboardUiIntent
    data object OnOvertimeClicked : DashboardUiIntent
    data object OnInsuranceClicked : DashboardUiIntent
    data object OnHistoryClicked : DashboardUiIntent
    data object OnHolidaysClicked : DashboardUiIntent
    data object OnNotificationClicked : DashboardUiIntent
    data object OnEditToolsClicked : DashboardUiIntent
    data class OnBottomNavItemSelected(val index: Int) : DashboardUiIntent
}
