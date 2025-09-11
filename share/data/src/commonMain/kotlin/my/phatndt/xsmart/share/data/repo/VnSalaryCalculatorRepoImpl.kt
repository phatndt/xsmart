package my.phatndt.xsmart.share.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.repo.VnSalaryCalculatorRepo

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
