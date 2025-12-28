package my.xsmart.feature.salarycalculator.ui.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiEffect
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiIntent
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiState

class ResultViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    private val _effect = Channel<ResultUiEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        // Get calculation data from navigation arguments
        val calculationData = savedStateHandle.get<VnSalaryCalculatorEntity>("calculationData")
        _uiState.update { it.copy(calculationData = calculationData) }
    }

    fun setIntent(intent: ResultUiIntent) {
        when (intent) {
            ResultUiIntent.Recalculate -> handleRecalculate()
            ResultUiIntent.SharePdf -> handleSharePdf()
            ResultUiIntent.NavigateBack -> handleNavigateBack()
            ResultUiIntent.ViewChart -> handleViewChart()
            ResultUiIntent.ViewTaxStructure -> handleViewTaxStructure()
        }
    }

    private fun handleRecalculate() {
        viewModelScope.launch {
            _effect.send(ResultUiEffect.NavigateBackToInput)
        }
    }

    private fun handleSharePdf() {
        viewModelScope.launch {
            _effect.send(ResultUiEffect.ShowShareDialog)
        }
    }

    private fun handleNavigateBack() {
        viewModelScope.launch {
            _effect.send(ResultUiEffect.NavigateBackToInput)
        }
    }

    private fun handleViewChart() {
        // TODO: Implement chart view
    }

    private fun handleViewTaxStructure() {
        // TODO: Implement tax structure view
    }
}
