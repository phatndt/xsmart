package my.xsmart.feature.salarycalculator.ui.config.state

import my.xsmart.feature.salarycalculator.ui.config.model.ConfigMode
import my.xsmart.share.android.base.UiIntent

sealed interface SalaryConfigUiIntent : UiIntent {
    data class ChangeMode(val mode: ConfigMode) : SalaryConfigUiIntent
    data class UpdatePersonalDeduction(val value: String) : SalaryConfigUiIntent
    data class UpdateDependentDeduction(val value: String) : SalaryConfigUiIntent
    data object ResetToDefault : SalaryConfigUiIntent
    data object SaveConfig : SalaryConfigUiIntent
}
