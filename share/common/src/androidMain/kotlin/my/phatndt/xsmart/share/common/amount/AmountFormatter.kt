package my.phatndt.xsmart.share.common.amount

import android.icu.math.BigDecimal
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.icu.text.NumberFormat
import android.icu.util.ULocale
import my.phatndt.xsmart.utils.AndroidUtils

actual object AmountFormatter {
    private val locale: ULocale = ULocale.US

    var decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale)
        private set

    actual fun toDisplayAmount(value: KmmBigDecimal?): String {
        // Return an empty string if the value is null
        value ?: return ""

        return if (AndroidUtils.isAtLeastR()) {
            // Use NumberFormatter for API level 30 (Android R) and above
            NumberFormatter
                .withLocale(locale)
                .precision(Precision.maxFraction(2))
                .symbols(decimalFormatSymbols)
                .format(value)
                .toString()
        } else {
            // Use NumberFormat for devices with API level below 30
            (NumberFormat.getNumberInstance(locale.toLocale()) as DecimalFormat)
                .apply {
                    decimalFormatSymbols = this@AmountFormatter.decimalFormatSymbols
                    maximumFractionDigits = 2
                }
                .format(value)
        }
    }

    actual fun toDisplayAmount(value: String?): String {
        if (value.isNullOrEmpty()) return ""

        val convertedDouble = value
            .replace(decimalFormatSymbols.groupingSeparator.toString(), "")
            .toDoubleOrNull()

        return if (AndroidUtils.isAtLeastR()) {
            // Use NumberFormatter for API level 30 (Android R) and above
            NumberFormatter
                .withLocale(locale)
                .symbols(decimalFormatSymbols)
                .format(convertedDouble)
                .toString()
        } else {
            // Use NumberFormat for devices with API level below 30
            (NumberFormat.getNumberInstance(locale.toLocale()) as DecimalFormat)
                .apply {
                    decimalFormatSymbols = this@AmountFormatter.decimalFormatSymbols
                }
                .format(convertedDouble)
        }
    }

    actual fun parseAmount(value: String?): KmmBigDecimal? {
        if (value.isNullOrEmpty()) return null

        val trimValue = value.replace(decimalFormatSymbols.groupingSeparator.toString(), "")

        val numberFormat = NumberFormat.getNumberInstance(locale)

        return if (numberFormat is DecimalFormat) {
            numberFormat.isParseBigDecimal = true
            (numberFormat.parse(trimValue) as? BigDecimal)?.toBigDecimal()
        } else {
            null
        }
    }
}
