package my.xsmart.feature.salarycalculator.ui.config.data

import my.xsmart.feature.salarycalculator.ui.config.model.ConfigMode
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfig
import my.xsmart.feature.salarycalculator.ui.config.model.TaxBracket
import my.xsmart.feature.salarycalculator.ui.config.model.TaxBracketColorTheme
import java.math.BigDecimal

object ConfigConstants {
    val BRACKETS_BEFORE_2026 = listOf(
        TaxBracket(id = 1, label = "Up to 5M", rate = 5, colorTheme = TaxBracketColorTheme.GREEN),
        TaxBracket(id = 2, label = "5M to 10M", rate = 10, colorTheme = TaxBracketColorTheme.BLUE),
        TaxBracket(id = 3, label = "10M to 18M", rate = 15, colorTheme = TaxBracketColorTheme.INDIGO),
        TaxBracket(id = 4, label = "18M to 32M", rate = 20, colorTheme = TaxBracketColorTheme.PURPLE),
        TaxBracket(id = 5, label = "32M to 52M", rate = 25, colorTheme = TaxBracketColorTheme.PINK),
        TaxBracket(id = 6, label = "52M to 80M", rate = 30, colorTheme = TaxBracketColorTheme.ORANGE),
        TaxBracket(id = 7, label = "Over 80M", rate = 35, colorTheme = TaxBracketColorTheme.RED),
    )

    val BRACKETS_AFTER_2026 = listOf(
        TaxBracket(id = 1, label = "Up to 10M", rate = 5, colorTheme = TaxBracketColorTheme.GREEN),
        TaxBracket(id = 2, label = "10M to 30M", rate = 10, colorTheme = TaxBracketColorTheme.BLUE),
        TaxBracket(id = 3, label = "30M to 60M", rate = 15, colorTheme = TaxBracketColorTheme.INDIGO),
        TaxBracket(id = 4, label = "60M to 100M", rate = 20, colorTheme = TaxBracketColorTheme.PURPLE),
        TaxBracket(id = 5, label = "Over 100M", rate = 25, colorTheme = TaxBracketColorTheme.PINK),
    )

    val CONFIGS = mapOf(
        ConfigMode.BEFORE_2026 to SalaryConfig(
            personalDeduction = BigDecimal(11_000_000L),
            dependentDeduction = BigDecimal(4_400_000L),
            brackets = BRACKETS_BEFORE_2026,
            description = "Current Regulation (Before 2026)",
            isEditable = false,
            official = true,
        ),
        ConfigMode.AFTER_2026 to SalaryConfig(
            personalDeduction = BigDecimal(11_000_000L),
            dependentDeduction = BigDecimal(4_400_000L),
            brackets = BRACKETS_AFTER_2026,
            description = "New Regulation (After 2026)",
            isEditable = false,
            official = true,
        ),
        ConfigMode.CUSTOM to SalaryConfig(
            personalDeduction = BigDecimal(11_000_000L),
            dependentDeduction = BigDecimal(4_400_000L),
            brackets = BRACKETS_AFTER_2026,
            description = "Custom Configuration",
            isEditable = true,
            official = false,
        ),
    )
}
