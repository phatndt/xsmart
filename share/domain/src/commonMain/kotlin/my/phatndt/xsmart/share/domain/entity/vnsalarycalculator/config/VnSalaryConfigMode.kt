package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

enum class VnSalaryConfigMode {
    BEFORE_2026,
    AFTER_2026,
    CUSTOM,
    ;

    fun isCustomMode() = this == CUSTOM
}
