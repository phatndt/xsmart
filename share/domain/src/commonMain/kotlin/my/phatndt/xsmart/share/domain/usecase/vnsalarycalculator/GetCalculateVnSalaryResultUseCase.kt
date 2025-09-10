package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface GetCalculateVnSalaryResultUseCase {
    operator fun invoke(): Flow<DataResult<VnSalaryCalculatorEntity>>
}
