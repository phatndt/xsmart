package my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state

import my.phatndt.xsmart.android.core.base.UiState
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity

data class VnSalaryCalculatorDetailUiState(
    val data: VnSalaryCalculatorEntity? = null,
    val isInitial: Boolean = false,
    val listOfOverviewSalaryData: List<ItemDataModel> = emptyList(),
    val listOfDetailSalaryData: List<ItemDataModel> = emptyList(),
) : UiState
