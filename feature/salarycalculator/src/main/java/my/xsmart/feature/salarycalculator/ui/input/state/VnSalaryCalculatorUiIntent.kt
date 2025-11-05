package my.phatndt.xsmart.android.features.vnsalarycalculator.main.state

import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area

sealed interface VnSalaryCalculatorUiIntent {
    data class IncomeChangeIntent(val value: String) : VnSalaryCalculatorUiIntent

    data class NumberOfDependentsChangeIntent(val value: String) : VnSalaryCalculatorUiIntent

    data class InsuranceSalaryChangeIntent(val value: String) : VnSalaryCalculatorUiIntent

    data class InsuranceTypeChangeIntent(val value: InsuranceType) : VnSalaryCalculatorUiIntent

    data class AreaChangeIntent(val value: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area) : VnSalaryCalculatorUiIntent

    data object CalculatorSalary : VnSalaryCalculatorUiIntent
}
