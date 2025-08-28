package my.phatndt.xsmart.domain.repo

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface VnSalaryCalculatorRepo {
    fun saveVnSalaryResult(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>>
    fun getVnSalaryResult(): Flow<DataResult<VnSalaryCalculatorEntity>>
}
