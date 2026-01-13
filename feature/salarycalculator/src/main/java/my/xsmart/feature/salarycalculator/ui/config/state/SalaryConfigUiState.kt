package my.xsmart.feature.salarycalculator.ui.config.state

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VietnamSalaryConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfigModel
import my.xsmart.share.android.base.UiState

data class SalaryConfigUiState(
    val currentMode: VnSalaryConfigMode = VnSalaryConfigMode.AFTER_2026,
    val currentConfigModel: SalaryConfigModel? = ConfigConstants.configsModel[VnSalaryConfigMode.AFTER_2026],
    val isLoading: Boolean = false,
) : UiState
