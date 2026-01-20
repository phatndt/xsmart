package my.xsmart.feature.salarycalculator.ui.config.model

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VietnamSalaryConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants.BRACKETS_AFTER_2026
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants.BRACKETS_BEFORE_2026
import java.math.BigDecimal

data class SalaryConfigModel(
    val config: VietnamSalaryConfig,
    val personalDeduction: String ,
    val dependentDeduction: String,
    val brackets: List<TaxBracket>,
    val description: String,
    val isEditable: Boolean,
    val official: Boolean,
)


fun VietnamSalaryConfig.toModel(
    mode: VnSalaryConfigMode,
) = SalaryConfigModel(
    config = this,
    personalDeduction = this.personalDeduction.toString(),
    dependentDeduction = this.dependentDeduction.toString(),
    brackets = when (mode) {
        VnSalaryConfigMode.BEFORE_2026 -> BRACKETS_BEFORE_2026
        VnSalaryConfigMode.AFTER_2026 -> BRACKETS_AFTER_2026
        VnSalaryConfigMode.CUSTOM -> BRACKETS_AFTER_2026
    },
    description = when (mode) {
        VnSalaryConfigMode.BEFORE_2026 -> "Current Regulation (Before 2026)"
        VnSalaryConfigMode.AFTER_2026 -> "New Regulation (After 2026)"
        VnSalaryConfigMode.CUSTOM -> "Custom Configuration"
    },
    isEditable = when (mode) {
        VnSalaryConfigMode.BEFORE_2026 -> false
        VnSalaryConfigMode.AFTER_2026 -> false
        VnSalaryConfigMode.CUSTOM -> true
    },
    official = when (mode) {
        VnSalaryConfigMode.BEFORE_2026 -> true
        VnSalaryConfigMode.AFTER_2026 -> true
        VnSalaryConfigMode.CUSTOM -> false
    },
)
