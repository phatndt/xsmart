package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.repo.VnSalaryCalculatorRepo

class SaveCalculateVnSalaryResultUseCaseImpl(
    private val vnSalaryCalculatorRepo: VnSalaryCalculatorRepo,
) : SaveCalculateVnSalaryResultUseCase {
    override fun invoke(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>> {
        return vnSalaryCalculatorRepo.saveVnSalaryResult(data)
    }
}
