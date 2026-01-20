package my.phatndt.xsmart.share.common.amount

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

actual object AmountFormatter {
    private val locale: Locale = Locale.US

    var decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale)
        private set

    val numberFormat: DecimalFormat
        get() = NumberFormat.getNumberInstance(locale) as DecimalFormat


    actual fun toDisplayAmount(value: KmmBigDecimal?): String {
        // Return an empty string if the value is null
        value ?: return ""

        return try {
            numberFormat
                .apply {
                    decimalFormatSymbols = this@AmountFormatter.decimalFormatSymbols
                    maximumFractionDigits = 2
                }
                .format(value)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    actual fun toDisplayAmount(value: String?): String {
        if (value.isNullOrEmpty()) return ""

        val convertedDouble = value
            .replace(decimalFormatSymbols.groupingSeparator.toString(), "")
            .toDoubleOrNull()

        return try {
            numberFormat
                .apply {
                    decimalFormatSymbols = this@AmountFormatter.decimalFormatSymbols
                    maximumFractionDigits = 2
                }
                .format(convertedDouble)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    actual fun parseAmount(value: String?): KmmBigDecimal? {
        if (value.isNullOrEmpty()) return null

        val format = NumberFormat.getNumberInstance(locale)
        if (format !is DecimalFormat) return null

        format.decimalFormatSymbols = decimalFormatSymbols
        format.isParseBigDecimal = true

        val cleaned = value
            .trim()
            .replace(decimalFormatSymbols.groupingSeparator.toString(), "")

        return try {
            when (val number = format.parse(cleaned)) {
                is BigDecimal -> number
                is Number -> number.toString().toBigDecimal()
                else -> null
            }
        } catch (e: Exception) {
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
