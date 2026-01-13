package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.delay
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfigModel

/**
 * Mock UseCase to save salary configuration
 * TODO: Replace with actual implementation that writes to DataStore/Repository
 */
class SaveSalaryConfigUseCase {
    suspend operator fun invoke(mode: VnSalaryConfigMode, config: SalaryConfigModel): Result<Unit> {
        // Simulate network/database delay
        delay(500)
        // Mock successful save
        return Result.success(Unit)
    }
}
