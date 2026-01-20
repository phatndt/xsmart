package my.xsmart.feature.salarycalculator.ui.config.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.CustomSalaryCalculatorConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryCalculatorConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryCalculatorConfig2026
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfigModel
import my.xsmart.feature.salarycalculator.ui.config.model.TaxBracket
import my.xsmart.feature.salarycalculator.ui.config.model.TaxBracketColorTheme
import java.math.BigDecimal

object ConfigConstants {

    val BRACKETS_BEFORE_2026 = VnSalaryConfigMap.oldConfig.taxBrackets.mapIndexed { index, bracket ->
        TaxBracket(
            id = index + 1,
            label = genTaxBracketLabel(
                bracket.upperBound,
                bracket.lowerBound,
            ),
            rate = (bracket.rate * 100).toInt(),
            colorTheme = rateToColor(bracket.rate),
        )
    }

    val BRACKETS_AFTER_2026 = VnSalaryConfigMap.newConfig.taxBrackets.mapIndexed { index, bracket ->
        TaxBracket(
            id = index + 1,
            label = genTaxBracketLabel(
                bracket.upperBound,
                bracket.lowerBound,
            ),
            rate = (bracket.rate * 100).toInt(),
            colorTheme = rateToColor(bracket.rate),
        )
    }

    var configsModel = VnSalaryConfigMap.configs.mapValues { (key, value) ->
        SalaryConfigModel(
            config = value,
            personalDeduction = value.personalDeduction.toString(),
            dependentDeduction = value.dependentDeduction.toString(),
            brackets = when (key) {
                VnSalaryConfigMode.BEFORE_2026 -> BRACKETS_BEFORE_2026
                VnSalaryConfigMode.AFTER_2026 -> BRACKETS_AFTER_2026
                VnSalaryConfigMode.CUSTOM -> BRACKETS_AFTER_2026
            },
            description = when (key) {
                VnSalaryConfigMode.BEFORE_2026 -> "Current Regulation (Before 2026)"
                VnSalaryConfigMode.AFTER_2026 -> "New Regulation (After 2026)"
                VnSalaryConfigMode.CUSTOM -> "Custom Configuration"
            },
            isEditable = when (key) {
                VnSalaryConfigMode.BEFORE_2026 -> false
                VnSalaryConfigMode.AFTER_2026 -> false
                VnSalaryConfigMode.CUSTOM -> true
            },
            official = when (key) {
                VnSalaryConfigMode.BEFORE_2026 -> true
                VnSalaryConfigMode.AFTER_2026 -> true
                VnSalaryConfigMode.CUSTOM -> false
            },
        )
    }

    fun rateToColor(rate: Double): TaxBracketColorTheme = when {
        rate <= 0.05 -> TaxBracketColorTheme.GREEN
        rate <= 0.10 -> TaxBracketColorTheme.BLUE
        rate <= 0.15 -> TaxBracketColorTheme.INDIGO
        rate <= 0.20 -> TaxBracketColorTheme.PURPLE
        rate <= 0.25 -> TaxBracketColorTheme.PINK
        rate <= 0.30 -> TaxBracketColorTheme.ORANGE
        else -> TaxBracketColorTheme.RED
    }

    fun genTaxBracketLabel(
        lowerBound: BigDecimal?,
        upperBound: BigDecimal?,
    ): String = when {
        lowerBound == null && upperBound == null -> ""
        lowerBound == null -> "Over ${AmountFormatter.toCompactFormat(upperBound)}"
        upperBound == null -> "Up to ${AmountFormatter.toCompactFormat(lowerBound)}"
        else -> "${AmountFormatter.toCompactFormat(upperBound)} - ${AmountFormatter.toCompactFormat(lowerBound)}"
    }

    fun txt() {
        var configsModel = VnSalaryConfigMap.configs.mapValues { (key, value) ->
            SalaryConfigModel(
                config = value,
                personalDeduction = value.personalDeduction.toString(),
                dependentDeduction = value.dependentDeduction.toString(),
                brackets = when (key) {
                    VnSalaryConfigMode.BEFORE_2026 -> BRACKETS_BEFORE_2026
                    VnSalaryConfigMode.AFTER_2026 -> BRACKETS_AFTER_2026
                    VnSalaryConfigMode.CUSTOM -> BRACKETS_AFTER_2026
                },
                description = when (key) {
                    VnSalaryConfigMode.BEFORE_2026 -> "Current Regulation (Before 2026)"
                    VnSalaryConfigMode.AFTER_2026 -> "New Regulation (After 2026)"
                    VnSalaryConfigMode.CUSTOM -> "Custom Configuration"
                },
                isEditable = when (key) {
                    VnSalaryConfigMode.BEFORE_2026 -> false
                    VnSalaryConfigMode.AFTER_2026 -> false
                    VnSalaryConfigMode.CUSTOM -> true
                },
                official = when (key) {
                    VnSalaryConfigMode.BEFORE_2026 -> true
                    VnSalaryConfigMode.AFTER_2026 -> true
                    VnSalaryConfigMode.CUSTOM -> false
                },
            )
        }
    }

}

/**
 * Composable version of genTaxBracketLabel that uses string resources
 */
@Composable
fun genTaxBracketLabelComposable(
    lowerBound: BigDecimal?,
    upperBound: BigDecimal?,
): String = when {
    lowerBound == null && upperBound == null -> ""
    lowerBound == null -> stringResource(R.string.label_tax_bracket_over, AmountFormatter.toCompactFormat(upperBound))
    upperBound == null -> stringResource(R.string.label_tax_bracket_up_to, AmountFormatter.toCompactFormat(lowerBound))
    else -> stringResource(
        R.string.label_tax_bracket_range,
        AmountFormatter.toCompactFormat(upperBound),
        AmountFormatter.toCompactFormat(lowerBound)
    )
}

/**
 * Get config mode description using string resources
 */
@Composable
fun getConfigModeDescription(mode: VnSalaryConfigMode): String = when (mode) {
    VnSalaryConfigMode.BEFORE_2026 -> stringResource(R.string.config_desc_before_2026)
    VnSalaryConfigMode.AFTER_2026 -> stringResource(R.string.config_desc_after_2026)
    VnSalaryConfigMode.CUSTOM -> stringResource(R.string.config_desc_custom)
}

