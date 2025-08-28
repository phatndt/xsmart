package my.phatndt.xsmart.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.domain.repo.VnSalaryCalculatorRepo
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

class SaveCalculateVnSalaryResultUseCaseImpl(
    private val vnSalaryCalculatorRepo: VnSalaryCalculatorRepo,
) : SaveCalculateVnSalaryResultUseCase {
    override fun invoke(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>> {
        return vnSalaryCalculatorRepo.saveVnSalaryResult(data)
    }
}
