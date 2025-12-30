package my.phatndt.xsmart.share.common.amount

import platform.Foundation.NSDecimalNumber

actual class KmmBigDecimal(val nsDecimalNumber: NSDecimalNumber) : Comparable<KmmBigDecimal> {
    actual constructor(value: String) : this(NSDecimalNumber(value))

    actual constructor(value: Double) : this(NSDecimalNumber(value))

    actual constructor(value: Int) : this(NSDecimalNumber(value))

    actual override operator fun compareTo(other: KmmBigDecimal): Int {
        return this.nsDecimalNumber
            .compare(other.nsDecimalNumber)
            .toInt()
    }
}

actual operator fun KmmBigDecimal.plus(other: KmmBigDecimal): KmmBigDecimal {
    return KmmBigDecimal(this.nsDecimalNumber.decimalNumberByAdding(other.nsDecimalNumber))
}

actual operator fun KmmBigDecimal.times(other: KmmBigDecimal): KmmBigDecimal {
    return KmmBigDecimal(this.nsDecimalNumber.decimalNumberByMultiplyingBy(other.nsDecimalNumber))
}

actual operator fun KmmBigDecimal.minus(other: KmmBigDecimal): KmmBigDecimal {
    return KmmBigDecimal(this.nsDecimalNumber.decimalNumberBySubtracting(other.nsDecimalNumber))
}

actual operator fun KmmBigDecimal.div(other: KmmBigDecimal): KmmBigDecimal {
    return KmmBigDecimal(this.nsDecimalNumber.decimalNumberByDividingBy(other.nsDecimalNumber))
}
