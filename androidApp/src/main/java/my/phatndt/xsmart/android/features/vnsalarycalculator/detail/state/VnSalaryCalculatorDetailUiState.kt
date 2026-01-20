package my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state

import my.xsmart.share.android.base.UiState

data class VnSalaryCalculatorDetailUiState(
    val data: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity? = null,
    val isInitial: Boolean = false,
    val listOfOverviewSalaryData: List<ItemDataModel> = emptyList(),
    val listOfDetailSalaryData: List<ItemDataModel> = emptyList(),
) : UiState
