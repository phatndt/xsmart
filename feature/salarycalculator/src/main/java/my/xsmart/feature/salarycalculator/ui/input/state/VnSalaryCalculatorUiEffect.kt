package my.phatndt.xsmart.android.features.vnsalarycalculator.main.state

import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity

sealed interface VnSalaryCalculatorUiEffect {
    data object ShowDialogCanNotCalculateSalary : VnSalaryCalculatorUiEffect
    data class NavigateToDetailSalary(val data: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity) : VnSalaryCalculatorUiEffect
}
