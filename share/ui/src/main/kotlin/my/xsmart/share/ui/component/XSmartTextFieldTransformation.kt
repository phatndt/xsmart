package my.xsmart.share.ui.component

import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.icu.text.NumberFormat
import android.icu.util.ULocale
import android.os.Build
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class XSmartTextFieldTransformation : VisualTransformation {
    private val locale: ULocale = ULocale.US

    private val decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale)

    private val regex = Regex("^-?\\d+(,\\d+)*(\\.\\d+(e\\d+)?)?\$")

    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text

        if (raw.isEmpty() || !regex.matches(raw)) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        // remove separator to parse
        val numeric = raw.replace(decimalFormatSymbols.groupingSeparator.toString(), "")

        val formatted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Use NumberFormatter for API level 30 (Android R) and above
            NumberFormatter
                .withLocale(locale)
                .precision(Precision.maxFraction(2))
                .symbols(decimalFormatSymbols)
                .format(numeric.toBigDecimalOrNull() ?: 0)
                .toString()
        } else {
            // Use NumberFormat for devices with API level below 30
            (NumberFormat.getNumberInstance(locale.toLocale()) as DecimalFormat)
                .apply {
                    decimalFormatSymbols = this@XSmartTextFieldTransformation.decimalFormatSymbols
                    maximumFractionDigits = 2
                }
                .format(numeric.toBigDecimalOrNull() ?: 0)
        }

        return TransformedText(
            AnnotatedString(formatted),
            getOffsetMapping(raw, formatted)
        )
    }

    private fun getOffsetMapping(original: String, transformed: String): OffsetMapping {
        return object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // count how many separators added before this offset
                val raw = original.take(offset).replace(",", "")
                val formattedPrefix = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    // Use NumberFormatter for API level 30 (Android R) and above
                    NumberFormatter
                        .withLocale(locale)
                        .precision(Precision.maxFraction(2))
                        .symbols(decimalFormatSymbols)
                        .format(raw.toBigDecimalOrNull() ?: 0)
                        .toString()
                } else {
                    // Use NumberFormat for devices with API level below 30
                    (NumberFormat.getNumberInstance(locale.toLocale()) as DecimalFormat)
                        .apply {
                            decimalFormatSymbols = this@XSmartTextFieldTransformation.decimalFormatSymbols
                            maximumFractionDigits = 2
                        }
                        .format(raw.toBigDecimalOrNull() ?: 0)
                }
                return formattedPrefix.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                // remove separators up to this point
                val cleaned = transformed.take(offset).replace(",", "")
                return cleaned.length
            }
        }
    }
}
