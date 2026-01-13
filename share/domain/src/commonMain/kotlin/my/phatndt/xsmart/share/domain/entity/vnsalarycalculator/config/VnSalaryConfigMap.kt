package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

object VnSalaryConfigMap {

    internal var currentConfig: VietnamSalaryConfig? = null

    internal var currentMode: VnSalaryConfigMode? = null

    val oldConfig by lazy {
        VnSalaryCalculatorConfig()
    }

    val newConfig by lazy {
        VnSalaryCalculatorConfig2026()
    }

    val customConfig by lazy {
        CustomSalaryCalculatorConfig(
            personalDeduction = newConfig.personalDeduction,
            dependentDeduction = newConfig.dependentDeduction,
            taxBrackets = newConfig.taxBrackets,
        )
    }

    val configs = mapOf(
        VnSalaryConfigMode.BEFORE_2026 to oldConfig,
        VnSalaryConfigMode.AFTER_2026 to newConfig,
        VnSalaryConfigMode.CUSTOM to customConfig,
    )
}
