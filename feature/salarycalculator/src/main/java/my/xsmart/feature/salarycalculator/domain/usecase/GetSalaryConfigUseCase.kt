package my.xsmart.feature.salarycalculator.domain.usecase

import kotlinx.coroutines.delay
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.xsmart.feature.salarycalculator.ui.config.model.ConfigMode
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfig

/**
 * Mock UseCase to get salary configuration
 * TODO: Replace with actual implementation that reads from DataStore/Repository
 */
class GetSalaryConfigUseCase {
    suspend operator fun invoke(mode: ConfigMode): SalaryConfig {
        // Simulate network/database delay
        delay(300)
        return ConfigConstants.CONFIGS[mode] ?: ConfigConstants.CONFIGS[ConfigMode.BEFORE_2026]!!
    }
}
