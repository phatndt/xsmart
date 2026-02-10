package my.xsmart.feature.salarycalculator.ui.input.state

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceType
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.xsmart.share.android.base.UiState

data class InputSalaryUiState(
    val income: String? = null,
    val numberOfDependents: Int = 0,
    val insuranceSalary: String? = null,
    val insuranceType: InsuranceType = InsuranceType.FULL,
    val area: Area = Area.I,
    val calculatorMode: CalculatorMode = CalculatorMode.GROSS_TO_NET,
    val allowance: String? = "",
    val allowanceType: AllowanceType = AllowanceType.SEPARATED,
    val unionFeeEnabled: Boolean = false,
    val additionalIncome: String? = null,
): UiState
