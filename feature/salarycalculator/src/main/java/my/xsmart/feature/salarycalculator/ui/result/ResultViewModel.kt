package my.xsmart.feature.salarycalculator.ui.result

import android.util.Log
import androidx.lifecycle.viewModelScope
import my.phatndt.xsmart.share.common.amount.ZERO
import my.phatndt.xsmart.share.common.flowx.collectFold
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegment
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegmentType
import my.xsmart.feature.salarycalculator.ui.result.state.DetailedCalculationUiState
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiEffect
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiIntent
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiState
import my.xsmart.feature.salarycalculator.ui.result.state.TaxBracketModel
import my.xsmart.share.android.base.BaseViewModel

class ResultViewModel(
    private val getCalculateVnSalaryResultUseCase: GetCalculateVnSalaryResultUseCase,
) : BaseViewModel<ResultUiState, ResultUiIntent, ResultUiEffect>() {

    override fun createInitialState(): ResultUiState = ResultUiState()

    init {
        // Get calculation data from navigation arguments
//        val calculationData = savedStateHandle.get<VnSalaryCalculatorEntity>("calculationData")
    }

    override fun handleIntent(intent: ResultUiIntent) {
        when (intent) {
            ResultUiIntent.Recalculate -> handleRecalculate()
            ResultUiIntent.SharePdf -> handleSharePdf()
            ResultUiIntent.NavigateBack -> handleNavigateBack()
            ResultUiIntent.ViewChart -> handleViewChart()
            ResultUiIntent.ViewTaxStructure -> handleViewTaxStructure()
        }
    }

    private fun handleRecalculate() {
        handleSideEffects {
            ResultUiEffect.NavigateBackToInput
        }
    }

    private fun handleSharePdf() {
        handleSideEffects {
            ResultUiEffect.ShowShareDialog
        }
    }

    private fun handleNavigateBack() {
        handleSideEffects {
            ResultUiEffect.NavigateBackToInput
        }
    }

    private fun handleViewChart() {
        // TODO: Implement chart view
    }

    private fun handleViewTaxStructure() {
        // TODO: Implement tax structure view
    }

    fun loadData() {
        if (uiState.value.isLoading) return

        setUiState {
            copy(isLoading = false)
        }
        viewModelScope.launchWithoutException {
            getCalculateVnSalaryResultUseCase().collectFold(
                onSuccess = { salary ->
                    setUiState {
                        copy(
                            calculationData = salary,
                            salaryBreakdownItems = listOf(
                                BreakdownSegment(
                                    type = BreakdownSegmentType.NET_SALARY,
                                    percentage = (salary.netSalary / salary.grossSalary).toDouble() * 100,
                                    amount = salary.netSalary,
                                ),
                                BreakdownSegment(
                                    type = BreakdownSegmentType.INSURANCE,
                                    percentage = (salary.insurance.totalInsurance / salary.grossSalary).toDouble() * 100,
                                    amount = salary.insurance.totalInsurance,
                                ),
                                BreakdownSegment(
                                    type = BreakdownSegmentType.TAX,
                                    percentage = (salary.taxInfo.totalTax / salary.grossSalary).toDouble() * 100,
                                    amount = salary.taxInfo.totalTax,
                                ),
                            ),
                            detailedCalculationUiState = DetailedCalculationUiState(
                                data = salary,
                                taxBrackets = salary.taxInfo.taxBrackets.map { bracket ->
                                    TaxBracketModel(
                                        percent = (bracket.second.rate * 100).toInt(),
                                        min = bracket.second.lowerBound,
                                        max = bracket.second.upperBound,
                                        amount = bracket.first,
                                        isActive = bracket.first != ZERO
                                    )
                                },
                            ),
                        )
                    }
                },
                onError = {
                    Log.d(TAG, it.toString())
                },
            )
        }
    }

    private fun normalizeToPercentages(data: List<Double>): List<Double> {
        // 1. Calculate the sum of all values in the list
        val sum = data.sum()

        // 2. Check if the sum is zero to prevent division by zero
        if (sum == 0.0) {
            // Return a list of zeros if the sum is zero
            return List(data.size) { 0.0 }
        }

        // 3. Normalize each value: divide by the sum and multiply by 100
        return data.map { value ->
            (value / sum) * 100
        }
    }
}
