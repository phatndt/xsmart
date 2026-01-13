package my.xsmart.feature.salarycalculator.ui.config.model

data class TaxBracket(
    val id: Int,
    val label: String,
    val rate: Int,
    val colorTheme: TaxBracketColorTheme,
)

enum class TaxBracketColorTheme {
    GREEN,
    BLUE,
    INDIGO,
    PURPLE,
    PINK,
    ORANGE,
    RED,
}
