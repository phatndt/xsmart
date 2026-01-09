package my.xsmart.feature.salarycalculator.ui.config

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.xsmart.feature.salarycalculator.domain.usecase.GetSalaryConfigUseCase
import my.xsmart.feature.salarycalculator.domain.usecase.SaveSalaryConfigUseCase
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.xsmart.feature.salarycalculator.ui.config.model.ConfigMode
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiEffect
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiIntent
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiState
import my.xsmart.share.android.base.BaseViewModel

class SalaryConfigViewModel(
    private val getSalaryConfigUseCase: GetSalaryConfigUseCase,
    private val saveSalaryConfigUseCase: SaveSalaryConfigUseCase,
) : BaseViewModel<SalaryConfigUiState, SalaryConfigUiIntent, SalaryConfigUiEffect>() {

    override fun createInitialState(): SalaryConfigUiState {
        return SalaryConfigUiState()
    }

    init {
        loadConfig(ConfigMode.BEFORE_2026)
    }

    override fun handleIntent(intent: SalaryConfigUiIntent) {
        when (intent) {
            is SalaryConfigUiIntent.ChangeMode -> handleChangeMode(intent.mode)
            is SalaryConfigUiIntent.UpdatePersonalDeduction -> handleUpdatePersonalDeduction(intent.value)
            is SalaryConfigUiIntent.UpdateDependentDeduction -> handleUpdateDependentDeduction(intent.value)
            SalaryConfigUiIntent.ResetToDefault -> handleResetToDefault()
            SalaryConfigUiIntent.SaveConfig -> handleSaveConfig()
        }
    }

    private fun handleChangeMode(mode: ConfigMode) {
        loadConfig(mode)
    }

    private fun handleUpdatePersonalDeduction(value: String) {
        setUiState {
            copy(personalDeduction = value)
        }
    }

    private fun handleUpdateDependentDeduction(value: String) {
        setUiState {
            copy(dependentDeduction = value)
        }
    }

    private fun handleResetToDefault() {
        val currentMode = uiState.value.currentMode
        val defaultConfig = ConfigConstants.CONFIGS[currentMode]
        defaultConfig?.let {
            setUiState {
                copy(
                    personalDeduction = it.personalDeduction.toString(),
                    dependentDeduction = it.dependentDeduction.toString(),
                )
            }
        }
    }

    private fun handleSaveConfig() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            
            val currentState = uiState.value
            val updatedConfig = currentState.currentConfig?.copy(
                personalDeduction = currentState.personalDeduction.toBigDecimal(),
                dependentDeduction = currentState.dependentDeduction.toBigDecimal(),
            )
            
            if (updatedConfig != null) {
                saveSalaryConfigUseCase(currentState.currentMode, updatedConfig)
                    .onSuccess {
                        handleSideEffects { SalaryConfigUiEffect.ConfigSaved }
                        handleSideEffects { SalaryConfigUiEffect.NavigateBack }
                    }
            }
            
            setUiState { copy(isLoading = false) }
        }
    }

    private fun loadConfig(mode: ConfigMode) {
        viewModelScope.launch {
            setUiState { copy(isLoading = true, currentMode = mode) }
            
            val config = getSalaryConfigUseCase(mode)
            setUiState {
                copy(
                    currentConfig = config,
                    personalDeduction = config.personalDeduction.toString(),
                    dependentDeduction = config.dependentDeduction.toString(),
                    isLoading = false,
                )
            }
        }
    }
}
