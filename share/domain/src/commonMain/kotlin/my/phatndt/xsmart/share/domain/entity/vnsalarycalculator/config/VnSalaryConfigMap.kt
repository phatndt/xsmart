package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

object VnSalaryConfigMap {

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

    internal var currentConfig: VietnamSalaryConfig = configs[VnSalaryConfigMode.AFTER_2026] ?: newConfig

    internal var currentMode: VnSalaryConfigMode = VnSalaryConfigMode.AFTER_2026
}
