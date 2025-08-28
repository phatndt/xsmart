package my.phatndt.xsmart.model.entity.vnsalarycalculator

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

data class TaxBracket(
    val lowerBound: KmmBigDecimal, // Start of the tax bracket
    val upperBound: KmmBigDecimal?, // End of the tax bracket (null means no upper limit)
    val rate: KmmBigDecimal, // Tax rate for this bracket
)
