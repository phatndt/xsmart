package my.phatndt.xsmart.android.features.vnsalarycalculator.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import my.phatndt.xsmart.android.core.base.BaseViewModel
import my.phatndt.xsmart.android.core.base.UiIntent
import my.phatndt.xsmart.android.core.base.UiSideEffects
import my.phatndt.xsmart.android.core.utils.DeferredText
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state.ItemDataModel
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state.VnSalaryCalculatorDetailUiState
import my.phatndt.xsmart.core.shared.collectFold
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.AmountFormatter

class VnSalaryCalculatorDetailViewModel(
    private val getCalculateVnSalaryResultUseCase: GetCalculateVnSalaryResultUseCase,
) : BaseViewModel<VnSalaryCalculatorDetailUiState, UiIntent, UiSideEffects>() {

    override fun createInitialState(): VnSalaryCalculatorDetailUiState = VnSalaryCalculatorDetailUiState()

    override fun handleIntent(intent: UiIntent) {
    }

    fun loadData() {
        if (uiState.value.isInitial) return

        setUiState {
            copy(isInitial = false)
        }
        viewModelScope.launchWithoutException {
            getCalculateVnSalaryResultUseCase().collectFold(
                onSuccess = {
                    setUiState {
                        copy(
                            data = it,
                            listOfOverviewSalaryData = mapToListOfOverviewSalaryItemDataModel(it),
                            listOfDetailSalaryData = mapToListOfDetailSalaryItemDataModel(it),
                        )
                    }
                },
                onError = {
                    Log.d(TAG, it.toString())
                },
            )
        }
    }

    private fun mapToListOfOverviewSalaryItemDataModel(data: VnSalaryCalculatorEntity): List<ItemDataModel> {
        return listOf(
            ItemDataModel(
                title = DeferredText.Constant("Gross salary"),
                description = AmountFormatter.toDisplayAmount(data.grossSalary),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Insurance"),
                description = AmountFormatter.toDisplayAmount(data.insurance.totalInsurance),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Tax"),
                description = AmountFormatter.toDisplayAmount(data.tax),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Net salary"),
                description = AmountFormatter.toDisplayAmount(data.netSalary),
            ),
        )
    }

    private fun mapToListOfDetailSalaryItemDataModel(data: VnSalaryCalculatorEntity): List<ItemDataModel> {
        return listOf(
            ItemDataModel(
                title = DeferredText.Constant("Gross salary"),
                description = AmountFormatter.toDisplayAmount(data.grossSalary),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Social Insurance"),
                description = AmountFormatter.toDisplayAmount(data.insurance.socialInsurance),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Health Insurance"),
                description = AmountFormatter.toDisplayAmount(data.insurance.healthInsurance),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Unemployment Insurance"),
                description = AmountFormatter.toDisplayAmount(data.insurance.unemploymentInsurance),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Before tax"),
                description = AmountFormatter.toDisplayAmount(data.beforeTaxIncome),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Personal deduction"),
                description = AmountFormatter.toDisplayAmount(data.personalDeduction),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Dependent deduction"),
                description = AmountFormatter.toDisplayAmount(data.dependentDeduction),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Taxable income"),
                description = AmountFormatter.toDisplayAmount(data.taxableIncome),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Tax"),
                description = AmountFormatter.toDisplayAmount(data.tax),
            ),
            ItemDataModel(
                title = DeferredText.Constant("Net salary"),
                description = AmountFormatter.toDisplayAmount(data.netSalary),
            ),
        )
    }
}
