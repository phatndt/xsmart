package my.xsmart.feature.salarycalculator.ui.config.state

import my.xsmart.share.android.base.UiSideEffects

sealed interface SalaryConfigUiEffect : UiSideEffects {
    data object ShowSaveConfigFail: SalaryConfigUiEffect
    data object ConfigSaved : SalaryConfigUiEffect
    data object NavigateBack : SalaryConfigUiEffect
}
