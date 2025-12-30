package my.xsmart.feature.salarycalculator.ui.input

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceType
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.SalaryCalculatorRequest
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCase
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiEffect
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiIntent
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiState
import my.xsmart.share.android.base.BaseViewModel
import java.math.BigDecimal

class InputSalaryViewModel(
    private val calculateVnSalaryUseCase: CalculateVnSalaryUseCase,
    private val saveCalculateVnSalaryResultUseCase: SaveCalculateVnSalaryResultUseCase,
) : BaseViewModel<InputSalaryUiState, InputSalaryUiIntent, InputSalaryUiEffect>() {
    override fun createInitialState(): InputSalaryUiState = InputSalaryUiState()

    override fun handleIntent(intent: InputSalaryUiIntent) {
        when (intent) {
            is InputSalaryUiIntent.AreaChangeIntent -> setUiState {
                copy(area = intent.value)
            }

            is InputSalaryUiIntent.IncomeChangeIntent -> setUiState {
                copy(income = intent.value)
            }

            is InputSalaryUiIntent.InsuranceSalaryChangeIntent -> setUiState {
                copy(insuranceSalary = intent.value)
            }

            is InputSalaryUiIntent.InsuranceTypeChangeIntent -> setUiState {
                copy(
                    insuranceType = intent.value,
                    insuranceSalary = if (intent.value == InsuranceType.FULL) null else insuranceSalary,
                )
            }

            is InputSalaryUiIntent.NumberOfDependentsChangeIntent -> setUiState {
                copy(numberOfDependents = intent.value)
            }

            InputSalaryUiIntent.CalculatorSalary -> calculateVnSalary()

            is InputSalaryUiIntent.ChangeAllowanceMoney -> setUiState {
                copy(allowance = intent.value)
            }

            is InputSalaryUiIntent.ChangeAllowanceType -> setUiState {
                copy(allowanceType = intent.value)
            }
        }
    }

    private fun calculateVnSalary() {
        val income = AmountFormatter.parseAmount(uiState.value.income)
        val numberOfDependents = uiState.value.numberOfDependents
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
        area: Area,
        numberOfDependents: Int,
        mode: CalculatorMode,
    ) {
        calculateVnSalaryUseCase(
            request = SalaryCalculatorRequest(
                salary = income,
                insuranceSalary = insurance,
                area = area,
                dependents = numberOfDependents,
                allowances = BigDecimal.ZERO,
                allowanceType = AllowanceType.SEPARATED,
                calculatorMode = CalculatorMode.GROSS_TO_NET,
            )
        ).collect { result ->

            handleCalculationResult(result)
        }
    }

    private suspend fun handleCalculationResult(result: DataResult<VnSalaryCalculatorEntity>) {
        when (result) {
            is DataResult.Failure -> handleFailure()
            is DataResult.Success -> handleSuccess(result.data)
        }
    }

    private fun handleFailure() {
        handleSideEffects {
            InputSalaryUiEffect.ShowDialogCanNotCalculateSalary
        }
    }

    private suspend fun handleSuccess(salaryData: VnSalaryCalculatorEntity) {
        saveCalculateVnSalaryResultUseCase(salaryData).collect { result ->
            when (result) {
                is DataResult.Failure -> handleFailure()
                is DataResult.Success -> navigateToDetailSalary(salaryData)
            }
        }
    }

    private fun navigateToDetailSalary(salaryData: VnSalaryCalculatorEntity) {
        handleSideEffects {
            InputSalaryUiEffect.NavigateToDetailSalary(salaryData)
        }
    }
}
