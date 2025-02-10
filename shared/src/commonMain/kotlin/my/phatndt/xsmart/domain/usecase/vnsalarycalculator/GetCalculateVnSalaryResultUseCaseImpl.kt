package my.phatndt.xsmart.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.core.shared.DataResult
import my.phatndt.xsmart.domain.repo.VnSalaryCalculatorRepo
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

class GetCalculateVnSalaryResultUseCaseImpl(
    private val vnSalaryCalculatorRepo: VnSalaryCalculatorRepo,
) : GetCalculateVnSalaryResultUseCase {
    override fun invoke(): Flow<DataResult<VnSalaryCalculatorEntity>> {
        return vnSalaryCalculatorRepo.getVnSalaryResult()
    }
}
