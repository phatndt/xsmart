package my.xsmart.feature.salarycalculator.ui.config.model

import java.math.BigDecimal

data class SalaryConfig(
    val personalDeduction: BigDecimal,
    val dependentDeduction: BigDecimal,
    val brackets: List<TaxBracket>,
    val description: String,
    val isEditable: Boolean,
    val official: Boolean,
)
