package my.phatndt.xsmart.android.features.vnsalarycalculator.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.xsmart.share.android.base.BaseViewModel

import my.phatndt.xsmart.android.features.vnsalarycalculator.main.model.InsuranceType
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiEffect
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiIntent
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiState
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

class VnSalaryCalculatorViewModel(
    private val calculateVnSalaryUseCase: CalculateVnSalaryUseCase,
    private val saveCalculateVnSalaryResultUseCase: SaveCalculateVnSalaryResultUseCase,
) : BaseViewModel<VnSalaryCalculatorUiState, VnSalaryCalculatorUiIntent, VnSalaryCalculatorUiEffect>() {

    override fun createInitialState(): VnSalaryCalculatorUiState = VnSalaryCalculatorUiState()

    override fun handleIntent(intent: VnSalaryCalculatorUiIntent) = when (intent) {
        is VnSalaryCalculatorUiIntent.AreaChangeIntent -> setUiState {
            copy(area = intent.value)
        }

        is VnSalaryCalculatorUiIntent.IncomeChangeIntent -> setUiState {
            copy(income = intent.value)
        }

        is VnSalaryCalculatorUiIntent.InsuranceSalaryChangeIntent -> setUiState {
            copy(insuranceSalary = intent.value)
        }

        is VnSalaryCalculatorUiIntent.InsuranceTypeChangeIntent -> setUiState {
            copy(
                insuranceType = intent.value,
                insuranceSalary = if (intent.value == InsuranceType.FULL) null else insuranceSalary,
            )
        }

        is VnSalaryCalculatorUiIntent.NumberOfDependentsChangeIntent -> setUiState {
            copy(numberOfDependents = intent.value)
        }

        VnSalaryCalculatorUiIntent.CalculatorSalary -> calculateVnSalary()
    }

    private fun calculateVnSalary() {
        val income = AmountFormatter.parseAmount(uiState.value.income)
        val numberOfDependents = uiState.value.numberOfDependents?.toIntOrNull()
        val insurance = calculateInsuranceAmount()
        val area = uiState.value.area
        val mode = uiState.value.calculatorMode

        if (income == null || insurance == null || numberOfDependents == null) return

        viewModelScope.launch {
            performSalaryCalculation(income, insurance, area, numberOfDependents, mode)
        }
    }

    private fun calculateInsuranceAmount(): KmmBigDecimal? {
        return if (uiState.value.insuranceType == InsuranceType.FULL) {
            AmountFormatter.parseAmount(uiState.value.income)
        } else {
            AmountFormatter.parseAmount(uiState.value.insuranceSalary)
        }
    }

    private suspend fun performSalaryCalculation(
        income: KmmBigDecimal,
        insurance: KmmBigDecimal,
        area: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area,
        numberOfDependents: Int,
        mode: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode,
    ) {
        calculateVnSalaryUseCase(income, insurance, area, numberOfDependents, mode).collect { result ->
            handleCalculationResult(result)
        }
    }

    private suspend fun handleCalculationResult(result: DataResult<my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity>) {
        when (result) {
            is DataResult.Failure -> handleFailure()
            is DataResult.Success -> handleSuccess(result.data)
        }
    }

    private fun handleFailure() {
        handleSideEffects {
            VnSalaryCalculatorUiEffect.ShowDialogCanNotCalculateSalary
        }
    }

    private suspend fun handleSuccess(salaryData: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity) {
        saveCalculateVnSalaryResultUseCase(salaryData).collect { result ->
            when (result) {
                is DataResult.Failure -> handleFailure()
                is DataResult.Success -> navigateToDetailSalary(salaryData)
            }
        }
    }

    private fun navigateToDetailSalary(salaryData: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity) {
        handleSideEffects {
            VnSalaryCalculatorUiEffect.NavigateToDetailSalary(salaryData)
        }
    }
}
