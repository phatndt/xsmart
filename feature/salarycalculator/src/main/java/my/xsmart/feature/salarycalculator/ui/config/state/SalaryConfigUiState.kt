package my.xsmart.feature.salarycalculator.ui.config.state

import my.xsmart.feature.salarycalculator.ui.config.model.ConfigMode
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfig
import my.xsmart.share.android.base.UiState

data class SalaryConfigUiState(
    val currentMode: ConfigMode = ConfigMode.BEFORE_2026,
    val currentConfig: SalaryConfig? = null,
    val personalDeduction: String = "",
    val dependentDeduction: String = "",
    val isLoading: Boolean = false,
) : UiState
