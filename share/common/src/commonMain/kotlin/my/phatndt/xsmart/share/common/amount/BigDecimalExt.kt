package my.phatndt.xsmart.share.common.amount

val ZERO = KmmBigDecimal(0)

fun Double.toKmmBigDecimal() = KmmBigDecimal(this)
