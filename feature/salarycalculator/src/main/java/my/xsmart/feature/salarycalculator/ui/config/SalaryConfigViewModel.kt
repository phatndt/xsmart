package my.xsmart.feature.salarycalculator.ui.config

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.dataresult.DataResult
import my.phatndt.xsmart.share.common.dataresult.onSuccess
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.CustomSalaryCalculatorConfig
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.GetVnSalaryConfigModeUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.GetVnSalaryConfigUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.SaveVnSalaryConfigModeUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.SaveVnSalaryConfigUseCase
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.xsmart.feature.salarycalculator.ui.config.model.toModel
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiEffect
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiIntent
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiState
import my.xsmart.share.android.base.BaseViewModel

class SalaryConfigViewModel(
    private val getVnSalaryConfigUseCase: GetVnSalaryConfigUseCase,
    private val getVnSalaryConfigModeUseCase: GetVnSalaryConfigModeUseCase,
    private val saveVnSalaryConfigUseCase: SaveVnSalaryConfigUseCase,
    private val saveVnSalaryConfigModeUseCase: SaveVnSalaryConfigModeUseCase,
) : BaseViewModel<SalaryConfigUiState, SalaryConfigUiIntent, SalaryConfigUiEffect>() {

    override fun createInitialState(): SalaryConfigUiState {
        return SalaryConfigUiState()
    }

    init {
        viewModelScope.launch {
            // Load the current config mode from use case
            getVnSalaryConfigModeUseCase().collectLatest { result ->
                result.onSuccess { mode ->
                    loadDefaultConfig(mode)
                }
            }
        }
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
        changeMode(mode)
    }

    private fun handleUpdatePersonalDeduction(value: String) {
        setUiState {
            copy(
                currentConfigModel = uiState.value.currentConfigModel?.copy(
                    personalDeduction = value,
                    ),
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

            var newConfig = ConfigConstants.configsModel[uiState.value.currentMode]?.config
                ?: VnSalaryConfigMap.configs[uiState.value.currentMode] ?: return@launch // TODO handle exception

            if (uiState.value.currentMode.isCustomMode() && newConfig is CustomSalaryCalculatorConfig) {
                val personalDeductionAmount =
                    AmountFormatter.parseAmount(uiState.value.currentConfigModel?.personalDeduction)
                val dependentDeductionAmount =
                    AmountFormatter.parseAmount(uiState.value.currentConfigModel?.dependentDeduction)

                if (personalDeductionAmount != null && dependentDeductionAmount != null) {
                    newConfig = newConfig.copy(
                        personalDeduction = personalDeductionAmount,
                        dependentDeduction = dependentDeductionAmount,
                    )
                }
            }

            val configDeferred = async {
                saveVnSalaryConfigUseCase(newConfig).first()
            }

            val modeDeferred = async {
                saveVnSalaryConfigModeUseCase(uiState.value.currentMode).first()
            }

            val result = listOf(configDeferred, modeDeferred).awaitAll()

            if (result.any { it is DataResult.Failure }) {
                handleSideEffects { SalaryConfigUiEffect.ShowSaveConfigFail }
            } else {
                handleSideEffects { SalaryConfigUiEffect.ConfigSaved }
                handleSideEffects { SalaryConfigUiEffect.NavigateBack }
            }

            setUiState { copy(isLoading = false) }
        }
    }

    private fun loadDefaultConfig(defaultMode: VnSalaryConfigMode) {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }

            ConfigConstants.configsModel = VnSalaryConfigMap.configs.mapValues { (mode, config) ->
                if (defaultMode.isCustomMode() && config is CustomSalaryCalculatorConfig) {
                    getVnSalaryConfigUseCase()
                        .firstOrNull()
                        ?.getOrNull()
                        ?.toModel(mode) ?: config.toModel(mode)
                } else {
                    config.toModel(mode)
                }
            }

            val configModel = ConfigConstants.configsModel[defaultMode] ?: return@launch

            setUiState {
                copy(
                    currentMode = defaultMode,
                    currentConfigModel = configModel,
                    isLoading = false,
                )
            }
        }
    }

    private fun changeMode(mode: VnSalaryConfigMode) {
        viewModelScope.launch {
            val config = ConfigConstants.configsModel[mode] ?: return@launch

            setUiState {
                copy(
                    currentMode = mode,
                    currentConfigModel = config,
                    isLoading = false,
                )
            }
        }
    }
}
