package my.phatndt.xsmart.share.common.amount

import java.math.BigDecimal
import java.math.RoundingMode

actual typealias KmmBigDecimal = BigDecimal

actual operator fun KmmBigDecimal.plus(other: KmmBigDecimal): KmmBigDecimal {
    return this.add(other)
}

actual operator fun KmmBigDecimal.times(other: KmmBigDecimal): KmmBigDecimal {
    return this.multiply(other)
}

actual operator fun KmmBigDecimal.minus(other: KmmBigDecimal): KmmBigDecimal {
    return this.subtract(other)
}

actual operator fun KmmBigDecimal.div(other: KmmBigDecimal): KmmBigDecimal {
    return this.divide(other, RoundingMode.HALF_EVEN)
}
