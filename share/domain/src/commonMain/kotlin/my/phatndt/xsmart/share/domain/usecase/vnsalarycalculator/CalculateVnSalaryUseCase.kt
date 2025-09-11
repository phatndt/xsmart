package my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity

interface CalculateVnSalaryUseCase {
    operator fun invoke(
        salary: KmmBigDecimal,
        insuranceSalary: KmmBigDecimal,
        area: Area,
        numberOfDependents: Int,
        calculatorMode: CalculatorMode,
    ): Flow<DataResult<VnSalaryCalculatorEntity>>
}
