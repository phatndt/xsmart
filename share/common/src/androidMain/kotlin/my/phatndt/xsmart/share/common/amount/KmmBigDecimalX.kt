package my.phatndt.xsmart.share.common.amount

import java.math.BigDecimal
import java.math.RoundingMode

fun KmmBigDecimal.toPercentStringFromRatio(scale: Int = 2): String =
    this.multiply(BigDecimal(100))
        .setScale(scale, RoundingMode.HALF_UP)
        .stripTrailingZeros()
        .toPlainString() + "%"
