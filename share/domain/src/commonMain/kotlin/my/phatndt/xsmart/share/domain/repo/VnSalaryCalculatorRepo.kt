package my.phatndt.xsmart.share.domain.repo

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface VnSalaryCalculatorRepo {
    fun saveVnSalaryResult(data: VnSalaryCalculatorEntity): Flow<DataResult<Unit>>
    fun getVnSalaryResult(): Flow<DataResult<VnSalaryCalculatorEntity>>
}
