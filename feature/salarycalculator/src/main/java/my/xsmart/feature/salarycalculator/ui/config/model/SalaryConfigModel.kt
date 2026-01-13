package my.xsmart.feature.salarycalculator.ui.config.model

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VietnamSalaryConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
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
