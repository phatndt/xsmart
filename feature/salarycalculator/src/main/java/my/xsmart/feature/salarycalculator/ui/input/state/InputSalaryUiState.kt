package my.xsmart.feature.salarycalculator.ui.input.state

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.xsmart.share.android.base.UiState

data class InputSalaryUiState(
    val income: String? = null,
    val numberOfDependents: String? = null,
    val insuranceSalary: String? = null,
    val insuranceType: InsuranceType = InsuranceType.FULL,
    val area: Area = Area.I,
    val calculatorMode: CalculatorMode = CalculatorMode.GROSS_TO_NET,
): UiState
