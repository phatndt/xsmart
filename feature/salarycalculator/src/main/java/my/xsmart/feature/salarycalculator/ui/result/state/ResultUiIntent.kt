package my.xsmart.feature.salarycalculator.ui.result.state

import my.xsmart.share.android.base.UiIntent

sealed interface ResultUiIntent: UiIntent {
    data object Recalculate : ResultUiIntent
    data object SharePdf : ResultUiIntent
    data object NavigateBack : ResultUiIntent
    data object ViewChart : ResultUiIntent
    data object ViewTaxStructure : ResultUiIntent
}
