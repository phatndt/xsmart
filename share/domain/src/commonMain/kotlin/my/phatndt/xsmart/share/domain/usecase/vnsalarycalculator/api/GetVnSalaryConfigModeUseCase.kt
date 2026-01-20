package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode

fun interface GetVnSalaryConfigModeUseCase {
    operator fun invoke(): Flow<DataResult<VnSalaryConfigMode>>
}
