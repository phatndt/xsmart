package my.phatndt.xsmart.android.features.vnsalarycalculator.main.state

import my.phatndt.xsmart.android.core.base.UiState
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.model.InsuranceType
import my.phatndt.xsmart.model.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.model.entity.vnsalarycalculator.CalculatorMode

data class VnSalaryCalculatorUiState(
    val income: String? = null,
    val numberOfDependents: String? = null,
    val insuranceSalary: String? = null,
    val insuranceType: InsuranceType = InsuranceType.FULL,
    val area: Area = Area.I,
    val calculatorMode: CalculatorMode = CalculatorMode.GROSS_TO_NET,
) : UiState
