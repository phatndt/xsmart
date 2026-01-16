package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.GetVnSalaryConfigModeUseCase

class GetVnSalaryConfigModeUseCaseImpl : GetVnSalaryConfigModeUseCase {
    override fun invoke(): Flow<DataResult<VnSalaryConfigMode>> {
        return flowOf<DataResult<VnSalaryConfigMode>>(DataResult.Success(VnSalaryConfigMap.currentMode) )
    }
}
