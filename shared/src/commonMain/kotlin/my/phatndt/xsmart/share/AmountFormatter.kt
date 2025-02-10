package my.phatndt.xsmart.share

expect object AmountFormatter {
    fun toDisplayAmount(value: KmmBigDecimal?): String
    fun toDisplayAmount(value: String?): String
    fun parseAmount(value: String?): KmmBigDecimal?
}
