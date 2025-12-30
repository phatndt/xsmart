package my.xsmart.feature.salarycalculator.ui.input.state

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceType
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.xsmart.share.android.base.UiIntent

sealed interface InputSalaryUiIntent: UiIntent {
    data class IncomeChangeIntent(val value: String) : InputSalaryUiIntent

    data class NumberOfDependentsChangeIntent(val value: Int) : InputSalaryUiIntent

    data class InsuranceSalaryChangeIntent(val value: String) : InputSalaryUiIntent

    data class InsuranceTypeChangeIntent(val value: InsuranceType) : InputSalaryUiIntent

    data class AreaChangeIntent(val value: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area) :
        InputSalaryUiIntent

    data object CalculatorSalary : InputSalaryUiIntent

    data class ChangeAllowanceType(val value: AllowanceType) : InputSalaryUiIntent

    data class ChangeAllowanceMoney(val value: String) : InputSalaryUiIntent
}
