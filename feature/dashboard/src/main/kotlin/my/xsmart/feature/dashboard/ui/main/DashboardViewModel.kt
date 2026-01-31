package my.xsmart.feature.dashboard.ui.main

import my.xsmart.feature.dashboard.ui.main.state.DashboardUiEffect
import my.xsmart.feature.dashboard.ui.main.state.DashboardUiIntent
import my.xsmart.feature.dashboard.ui.main.state.DashboardUiState
import my.xsmart.share.android.base.BaseViewModel

class DashboardViewModel : BaseViewModel<DashboardUiState, DashboardUiIntent, DashboardUiEffect>() {

    override fun createInitialState(): DashboardUiState = DashboardUiState()

    override fun handleIntent(intent: DashboardUiIntent) {
        when (intent) {
            DashboardUiIntent.OnStartCalculatingClicked -> handleStartCalculating()
            DashboardUiIntent.OnSalaryCalcClicked -> handleSalaryCalc()
            DashboardUiIntent.OnTaxClicked -> handleTax()
            DashboardUiIntent.OnOvertimeClicked -> handleOvertime()
            DashboardUiIntent.OnInsuranceClicked -> handleInsurance()
            DashboardUiIntent.OnHistoryClicked -> handleHistory()
            DashboardUiIntent.OnHolidaysClicked -> handleHolidays()
            DashboardUiIntent.OnNotificationClicked -> handleNotification()
            DashboardUiIntent.OnEditToolsClicked -> handleEditTools()
            is DashboardUiIntent.OnBottomNavItemSelected -> handleBottomNavItemSelected(intent.index)
        }
    }

    private fun handleStartCalculating() {
        handleSideEffects { DashboardUiEffect.NavigateToSalaryCalculator }
    }

    private fun handleSalaryCalc() {
        handleSideEffects { DashboardUiEffect.NavigateToSalaryCalculator }
    }

    private fun handleTax() {
        handleSideEffects { DashboardUiEffect.NavigateToTaxInfo }
    }

    private fun handleOvertime() {
        handleSideEffects { DashboardUiEffect.NavigateToOvertimeTracker }
    }

    private fun handleInsurance() {
        handleSideEffects { DashboardUiEffect.NavigateToInsurance }
    }

    private fun handleHistory() {
        handleSideEffects { DashboardUiEffect.NavigateToHistory }
    }

    private fun handleHolidays() {
        handleSideEffects { DashboardUiEffect.NavigateToHolidays }
    }

    private fun handleNotification() {
        handleSideEffects { DashboardUiEffect.NavigateToNotifications }
    }

    private fun handleEditTools() {
        handleSideEffects { DashboardUiEffect.NavigateToEditTools }
    }

    private fun handleBottomNavItemSelected(index: Int) {
        setUiState { copy(selectedBottomNavItem = index) }
    }
}
