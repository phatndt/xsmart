package my.phatndt.xsmart.android.features.vnsalarycalculator.detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import my.phatndt.xsmart.android.core.utils.DeferredText
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state.ItemDataModel
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state.VnSalaryCalculatorDetailUiState
import my.phatndt.xsmart.share.common.flowx.collectFold
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.xsmart.share.android.base.BaseViewModel
import my.xsmart.share.android.base.UiIntent
import my.xsmart.share.android.base.UiSideEffects

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
                            listOfOverviewSalaryData = emptyList(),
                            listOfDetailSalaryData = emptyList(),
                        )
                    }
                },
                onError = {
                    Log.d(TAG, it.toString())
                },
            )
        }
    }

}
