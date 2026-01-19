package my.xsmart.feature.salarycalculator.ui.result

import android.util.Log
import androidx.lifecycle.viewModelScope
import my.phatndt.xsmart.share.common.amount.ZERO
import my.phatndt.xsmart.share.common.amount.toPercentStringFromRatio
import my.phatndt.xsmart.share.common.flowx.collectFold
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegment
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegmentType
import my.xsmart.feature.salarycalculator.ui.result.state.DetailedCalculationUiState
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiEffect
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiIntent
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiState
import my.xsmart.feature.salarycalculator.ui.result.state.TaxBracketModel
import my.xsmart.share.android.base.BaseViewModel
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor
import kotlin.math.roundToInt

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
                            salaryBreakdownItems = createSalaryBreakdownItems(salary),
                            detailedCalculationUiState = DetailedCalculationUiState(
                                data = salary,
                                taxBrackets = salary.taxInfo.taxBrackets.map { bracket ->
                                    TaxBracketModel(
                                        percent = (bracket.second.rate * 100).toInt(),
                                        min = bracket.second.lowerBound,
                                        max = bracket.second.upperBound,
                                        amount = bracket.first,
                                        isActive = bracket.first != ZERO,
                                    )
                                },
                                socialInsuranceRate = salary.config.socialInsuranceRate.toPercentStringFromRatio(),
                                healthInsuranceRate = salary.config.healthInsuranceRate.toPercentStringFromRatio(),
                                unemploymentInsuranceRate = salary.config.unemploymentInsuranceRate.toPercentStringFromRatio(),
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

    private fun createSalaryBreakdownItems(salary: VnSalaryCalculatorEntity): List<BreakdownSegment> {
        if (salary.grossSalary.signum() == 0) return emptyList()

        val hundred = BigDecimal("100")

        val netPercent = salary.netSalary
            .divide(salary.grossSalary, 10, RoundingMode.HALF_UP)
            .multiply(hundred)
            .setScale(0, RoundingMode.HALF_UP)
            .toDouble()

        val insurancePercent = minOf(
            salary.insurance.totalInsurance
                .divide(salary.grossSalary, 10, RoundingMode.HALF_UP)
                .multiply(hundred)
                .setScale(0, RoundingMode.HALF_UP)
                .toDouble(),
            100 - netPercent,
        )

        val taxPercentBd = minOf(
            salary.taxInfo.totalTax
                .divide(salary.grossSalary, 10, RoundingMode.HALF_UP)
                .multiply(hundred)
                .setScale(0, RoundingMode.HALF_UP)
                .toDouble(),
            100 - netPercent - insurancePercent,
        )

        val breakdownSegments: MutableList<BreakdownSegment> = mutableListOf()

        if (netPercent > 0.0) {
            breakdownSegments.add(
                BreakdownSegment(
                    type = BreakdownSegmentType.NET_SALARY,
                    percentage = netPercent,
                    amount = salary.netSalary,
                )
            )
        }

        if (insurancePercent > 0.0) {
            breakdownSegments.add(
                BreakdownSegment(
                    type = BreakdownSegmentType.INSURANCE,
                    percentage = insurancePercent,
                    amount = salary.insurance.totalInsurance,
                )
            )
        }

        if (taxPercentBd > 0.0) {
            breakdownSegments.add(
                BreakdownSegment(
                    type = BreakdownSegmentType.TAX,
                    percentage = taxPercentBd,
                    amount = salary.taxInfo.totalTax,
                )
            )
        }

        return breakdownSegments
    }
}
