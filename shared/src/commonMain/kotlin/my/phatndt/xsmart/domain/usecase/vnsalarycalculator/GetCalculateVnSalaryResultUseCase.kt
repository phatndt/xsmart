package my.phatndt.xsmart.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface GetCalculateVnSalaryResultUseCase {
    operator fun invoke(): Flow<DataResult<VnSalaryCalculatorEntity>>
}
