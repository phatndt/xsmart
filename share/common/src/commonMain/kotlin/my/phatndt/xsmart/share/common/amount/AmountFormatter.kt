package my.phatndt.xsmart.share.common.amount

expect object AmountFormatter {
    fun toDisplayAmount(value: KmmBigDecimal?): String
    fun toDisplayAmount(value: String?): String
    fun parseAmount(value: String?): KmmBigDecimal?
}
