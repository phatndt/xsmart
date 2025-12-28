package my.xsmart.feature.salarycalculator.ui.result.state

sealed interface ResultUiEffect {
    data object NavigateBackToInput : ResultUiEffect
    data object ShowShareDialog : ResultUiEffect
    data class ShowError(val message: String) : ResultUiEffect
}
