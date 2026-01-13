package my.xsmart.feature.salarycalculator.ui.config

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetSalaryConfigUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveSalaryConfigUseCase
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
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
        loadConfig(VnSalaryConfigMode.BEFORE_2026)
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

    private fun handleChangeMode(mode: VnSalaryConfigMode) {
        loadConfig(mode)
    }

    private fun handleUpdatePersonalDeduction(value: String) {
        setUiState {
            copy(
                currentConfigModel = uiState.value.currentConfigModel?.copy(personalDeduction = value),
            )
        }
    }

    private fun handleUpdateDependentDeduction(value: String) {
        setUiState {
            copy(
                currentConfigModel = uiState.value.currentConfigModel?.copy(dependentDeduction = value),
            )
        }
    }

    private fun handleResetToDefault() {
//        val currentMode = uiState.value.currentMode
//        val defaultConfig = ConfigConstants.CONFIGS[currentMode]
//        defaultConfig?.let {
//            setUiState {
//                copy(
//                    personalDeduction = it.personalDeduction.toString(),
//                    dependentDeduction = it.dependentDeduction.toString(),
//                )
//            }
//        }
    }

    private fun handleSaveConfig() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }

            val newConfig =  when(uiState.value.currentMode) {
                VnSalaryConfigMode.BEFORE_2026 -> VnSalaryConfigMap.oldConfig
                VnSalaryConfigMode.AFTER_2026 -> VnSalaryConfigMap.newConfig
                VnSalaryConfigMode.CUSTOM -> {
                    val personalDeductionAmount = AmountFormatter.parseAmount(uiState.value.currentConfigModel?.personalDeduction)
                    val dependentDeductionAmount =  AmountFormatter.parseAmount(uiState.value.currentConfigModel?.dependentDeduction)

                    if (personalDeductionAmount == null || dependentDeductionAmount == null) {
                        VnSalaryConfigMap.customConfig
                    } else {
                        VnSalaryConfigMap.customConfig.copy(
                            personalDeduction = personalDeductionAmount,
                            dependentDeduction = dependentDeductionAmount,
                        )
                    }
                }
            }
            saveSalaryConfigUseCase(currentState.currentMode, updatedConfig)
//                    .onSuccess {
//                        handleSideEffects { SalaryConfigUiEffect.ConfigSaved }
//                        handleSideEffects { SalaryConfigUiEffect.NavigateBack }
//                    }
            setUiState { copy(isLoading = false) }
        }
    }

    private fun loadConfig(mode: VnSalaryConfigMode) {
        viewModelScope.launch {
            setUiState { copy(isLoading = true, currentMode = mode) }

            val config = ConfigConstants.configsModel[mode] ?: return@launch
            setUiState {
                copy(
                    currentConfigModel = config,
                    isLoading = false,
                )
            }
        }
    }
}
