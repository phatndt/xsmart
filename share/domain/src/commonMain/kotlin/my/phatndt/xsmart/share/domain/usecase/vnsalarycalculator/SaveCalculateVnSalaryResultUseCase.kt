package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface SaveCalculateVnSalaryResultUseCase {
    operator fun invoke(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>>
}
