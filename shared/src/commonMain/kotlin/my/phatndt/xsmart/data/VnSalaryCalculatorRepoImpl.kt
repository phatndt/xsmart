package my.phatndt.xsmart.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import my.phatndt.xsmart.core.shared.DataResult
import my.phatndt.xsmart.domain.repo.VnSalaryCalculatorRepo
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

class VnSalaryCalculatorRepoImpl : VnSalaryCalculatorRepo {
    private var vnSalaryResult: VnSalaryCalculatorEntity? = null

    override fun saveVnSalaryResult(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>> {
        vnSalaryResult = data
        return flowOf(DataResult.Success(Unit))
    }

    override fun getVnSalaryResult(): Flow<DataResult<VnSalaryCalculatorEntity>> = flow {
        vnSalaryResult?.let {
            emit(DataResult.Success(it))
        } ?: emit(DataResult.Failure(NullPointerException()))
    }
}
