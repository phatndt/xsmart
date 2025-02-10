package my.phatndt.xsmart.share

actual object AmountFormatter {
    actual fun toDisplayAmount(value: KmmBigDecimal?): String {
        return ""
    }

    actual fun toDisplayAmount(value: String?): String {
        return ""
    }

    actual fun parseAmount(value: String?): KmmBigDecimal? {
        return KmmBigDecimal(value.orEmpty())
    }
}
