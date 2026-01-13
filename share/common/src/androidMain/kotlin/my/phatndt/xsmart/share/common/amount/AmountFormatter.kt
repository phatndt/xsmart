package my.phatndt.xsmart.share.common.amount

import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.icu.text.NumberFormat
import android.icu.util.ULocale
import my.phatndt.xsmart.utils.AndroidUtils
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.text.compareTo

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
            (numberFormat.parse(trimValue) as? BigDecimal)
        } else {
            null
        }
    }

    actual fun toCompactFormat(value: KmmBigDecimal?): String {
        value ?: return ""

        val suffixes = arrayOf("", "K", "M", "B", "T", "P", "E")

        if (value.compareTo(BigDecimal.ZERO) == 0) return "0"

        val absValue = value.abs()

        val thousand = BigDecimal("1000")
        var base = 0
        var divisor = BigDecimal.ONE

        while (absValue >= divisor.multiply(thousand) && base < suffixes.lastIndex) {
            divisor = divisor.multiply(thousand)
            base++
        }

        if (base == 0) {
            return value.setScale(0, RoundingMode.HALF_UP).toPlainString()
        }

        val scaled = value.divide(divisor, 1, RoundingMode.HALF_UP)
            .stripTrailingZeros()

        return scaled.toPlainString() + suffixes[base]
    }

}
