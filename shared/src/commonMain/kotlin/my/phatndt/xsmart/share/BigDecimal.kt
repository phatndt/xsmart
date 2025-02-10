package my.phatndt.xsmart.share

expect class KmmBigDecimal : Comparable<KmmBigDecimal> {
    constructor(value: String)
    constructor(value: Double)
    constructor(value: Int)
    constructor(value: Long)
}

expect operator fun KmmBigDecimal.plus(other: KmmBigDecimal): KmmBigDecimal

expect operator fun KmmBigDecimal.times(other: KmmBigDecimal): KmmBigDecimal

expect operator fun KmmBigDecimal.minus(other: KmmBigDecimal): KmmBigDecimal

expect operator fun KmmBigDecimal.div(other: KmmBigDecimal): KmmBigDecimal
