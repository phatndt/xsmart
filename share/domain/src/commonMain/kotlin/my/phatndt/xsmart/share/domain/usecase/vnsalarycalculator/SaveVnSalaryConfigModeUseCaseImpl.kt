package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.SaveVnSalaryConfigModeUseCase

class SaveVnSalaryConfigModeUseCaseImpl : SaveVnSalaryConfigModeUseCase {
    override fun invoke(mode: VnSalaryConfigMode): Flow<DataResult<Unit>> {
        VnSalaryConfigMap.currentMode = mode
        return flowOf(DataResult.Success(Unit))
    }
}
