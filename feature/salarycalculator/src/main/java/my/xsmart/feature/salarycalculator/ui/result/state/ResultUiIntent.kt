package my.xsmart.feature.salarycalculator.ui.result.state

sealed interface ResultUiIntent {
    data object Recalculate : ResultUiIntent
    data object SharePdf : ResultUiIntent
    data object NavigateBack : ResultUiIntent
    data object ViewChart : ResultUiIntent
    data object ViewTaxStructure : ResultUiIntent
}
