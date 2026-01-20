package my.xsmart.feature.salarycalculator.ui.config.state

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.share.android.base.UiIntent

sealed interface SalaryConfigUiIntent : UiIntent {
    data class ChangeMode(val mode: VnSalaryConfigMode) : SalaryConfigUiIntent
    data class UpdatePersonalDeduction(val value: String) : SalaryConfigUiIntent
    data class UpdateDependentDeduction(val value: String) : SalaryConfigUiIntent
    data object ResetToDefault : SalaryConfigUiIntent
    data object SaveConfig : SalaryConfigUiIntent
}
