package my.xsmart.feature.salarycalculator.ui.result.state

import my.xsmart.share.android.base.UiSideEffects

sealed interface ResultUiEffect: UiSideEffects {
    data object NavigateBackToInput : ResultUiEffect
    data object ShowShareDialog : ResultUiEffect
    data class ShowError(val message: String) : ResultUiEffect
}
