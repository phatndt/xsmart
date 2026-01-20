package my.xsmart.feature.salarycalculator.ui.input.state

import my.xsmart.share.android.base.UiSideEffects

sealed interface InputSalaryUiEffect: UiSideEffects {
    data object ShowDialogCanNotCalculateSalary : InputSalaryUiEffect
    data class NavigateToDetailSalary(val data: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity) :
        InputSalaryUiEffect
}
