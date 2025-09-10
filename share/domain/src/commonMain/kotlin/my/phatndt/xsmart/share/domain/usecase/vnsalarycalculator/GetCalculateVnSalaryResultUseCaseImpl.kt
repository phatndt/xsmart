package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.repo.VnSalaryCalculatorRepo

class GetCalculateVnSalaryResultUseCaseImpl(
    private val vnSalaryCalculatorRepo: VnSalaryCalculatorRepo,
) : GetCalculateVnSalaryResultUseCase {
    override fun invoke(): Flow<DataResult<VnSalaryCalculatorEntity>> {
        return vnSalaryCalculatorRepo.getVnSalaryResult()
    }
}
