package my.phatndt.xsmart.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface SaveCalculateVnSalaryResultUseCase {
    operator fun invoke(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>>
}
