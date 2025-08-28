package my.phatndt.xsmart.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.model.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.model.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

interface CalculateVnSalaryUseCase {
    operator fun invoke(
        salary: KmmBigDecimal,
        insuranceSalary: KmmBigDecimal,
        area: Area,
        numberOfDependents: Int,
        calculatorMode: CalculatorMode,
    ): Flow<DataResult<VnSalaryCalculatorEntity>>
}
