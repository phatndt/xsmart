package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

data class TaxBracket(
    val lowerBound: KmmBigDecimal, // Start of the tax bracket
    val upperBound: KmmBigDecimal?, // End of the tax bracket (null means no upper limit)
    val rate: Double, // Tax rate for this bracket
)
