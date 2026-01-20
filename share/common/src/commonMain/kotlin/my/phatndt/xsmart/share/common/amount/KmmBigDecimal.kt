package my.phatndt.xsmart.share.common.amount

expect class KmmBigDecimal : Comparable<KmmBigDecimal> {
    constructor(value: String)
    constructor(value: Int)
    constructor(value: Double)
    override fun compareTo(other: KmmBigDecimal): Int
}

expect operator fun KmmBigDecimal.plus(other: KmmBigDecimal): KmmBigDecimal

expect operator fun KmmBigDecimal.times(other: KmmBigDecimal): KmmBigDecimal

expect operator fun KmmBigDecimal.minus(other: KmmBigDecimal): KmmBigDecimal

expect operator fun KmmBigDecimal.div(other: KmmBigDecimal): KmmBigDecimal
