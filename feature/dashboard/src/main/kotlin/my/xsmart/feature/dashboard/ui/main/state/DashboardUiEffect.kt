package my.xsmart.feature.dashboard.ui.main.state

import my.xsmart.share.android.base.UiSideEffects

sealed interface DashboardUiEffect : UiSideEffects {
    data object NavigateToSalaryCalculator : DashboardUiEffect
    data object NavigateToTaxInfo : DashboardUiEffect
    data object NavigateToOvertimeTracker : DashboardUiEffect
    data object NavigateToInsurance : DashboardUiEffect
    data object NavigateToHistory : DashboardUiEffect
    data object NavigateToHolidays : DashboardUiEffect
    data object NavigateToNotifications : DashboardUiEffect
    data object NavigateToEditTools : DashboardUiEffect
}
