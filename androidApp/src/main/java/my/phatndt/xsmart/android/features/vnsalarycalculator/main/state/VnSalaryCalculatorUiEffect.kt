package my.phatndt.xsmart.android.features.vnsalarycalculator.main.state

import my.phatndt.xsmart.android.core.base.UiSideEffects
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

sealed interface VnSalaryCalculatorUiEffect : UiSideEffects {
    data object ShowDialogCanNotCalculateSalary : VnSalaryCalculatorUiEffect
    data class NavigateToDetailSalary(val data: VnSalaryCalculatorEntity) : VnSalaryCalculatorUiEffect
}
