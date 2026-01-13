package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.delay
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.ui.config.model.SalaryConfigModel

/**
 * Mock UseCase to get salary configuration
 * TODO: Replace with actual implementation that reads from DataStore/Repository
 */
class GetSalaryConfigUseCase {
    suspend operator fun invoke(mode: VnSalaryConfigMode): SalaryConfigModel {
        // Simulate network/database delay
        delay(300)
        return ConfigConstants.configsModel[mode] ?: ConfigConstants.configsModel[VnSalaryConfigMode.BEFORE_2026]!!
    }
}
