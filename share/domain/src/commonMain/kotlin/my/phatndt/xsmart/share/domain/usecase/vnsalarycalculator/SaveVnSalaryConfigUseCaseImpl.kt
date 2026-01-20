package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VietnamSalaryConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.SaveVnSalaryConfigUseCase

class SaveVnSalaryConfigUseCaseImpl : SaveVnSalaryConfigUseCase {
    override fun invoke(config: VietnamSalaryConfig): Flow<DataResult<Unit>> {
        VnSalaryConfigMap.currentConfig = config
        return flowOf(DataResult.Success(Unit))
    }
}
