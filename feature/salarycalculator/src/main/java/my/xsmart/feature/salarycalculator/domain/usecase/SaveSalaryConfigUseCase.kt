package my.xsmart.feature.salarycalculator.domain.usecase

import kotlinx.coroutines.delay
import my.xsmart.feature.salarycalculator.ui.config.model.ConfigMode
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfig

/**
 * Mock UseCase to save salary configuration
 * TODO: Replace with actual implementation that writes to DataStore/Repository
 */
class SaveSalaryConfigUseCase {
    suspend operator fun invoke(mode: ConfigMode, config: SalaryConfig): Result<Unit> {
        // Simulate network/database delay
        delay(500)
        // Mock successful save
        return Result.success(Unit)
    }
}
