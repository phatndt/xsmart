package my.phatndt.xsmart.android.features.vnsalarycalculator.main.state

import my.phatndt.xsmart.android.core.base.UiIntent
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.model.InsuranceType
import my.phatndt.xsmart.model.entity.vnsalarycalculator.Area

sealed interface VnSalaryCalculatorUiIntent : UiIntent {
    data class IncomeChangeIntent(val value: String) : VnSalaryCalculatorUiIntent

    data class NumberOfDependentsChangeIntent(val value: String) : VnSalaryCalculatorUiIntent

    data class InsuranceSalaryChangeIntent(val value: String) : VnSalaryCalculatorUiIntent

    data class InsuranceTypeChangeIntent(val value: InsuranceType) : VnSalaryCalculatorUiIntent

    data class AreaChangeIntent(val value: Area) : VnSalaryCalculatorUiIntent

    data object CalculatorSalary : VnSalaryCalculatorUiIntent
}
