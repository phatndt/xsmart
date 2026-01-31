package my.xsmart.feature.dashboard.ui.main

sealed interface DashboardNavigationEvent {
    data object NavigateToSalaryCalculator : DashboardNavigationEvent
    data object NavigateToTaxInfo : DashboardNavigationEvent
    data object NavigateToOvertimeTracker : DashboardNavigationEvent
    data object NavigateToInsurance : DashboardNavigationEvent
    data object NavigateToHistory : DashboardNavigationEvent
    data object NavigateToHolidays : DashboardNavigationEvent
    data object NavigateToNotifications : DashboardNavigationEvent
    data object NavigateToEditTools : DashboardNavigationEvent
}
