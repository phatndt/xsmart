package my.phatndt.xsmart.android.features.currencyconverter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel.BmiUIState
import my.phatndt.xsmart.android.features.currencyconverter.presentation.state.CurrencyUIState
import my.phatndt.xsmart.core.data.NetworkResponse
import my.phatndt.xsmart.feature.currency.data.model.CountryModel
import my.phatndt.xsmart.feature.currency.data.model.CurrencyRate
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository

class CurrencyViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(CurrencyUIState())
    val uiState = _uiState.asStateFlow()
    private var countries = mutableListOf<CountryModel>()

    init {
        updateShowLoadingDialog(true)
        loadCountries()
        loadCurrencyInDay()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            countries = currencyRepository.loadCityFromLocalFile().toMutableList()
        }
    }

    private fun loadCurrencyInDay() {
        viewModelScope.launch {
            currencyRepository.getCurrencyInDate().collect {
                when (it) {
                    NetworkResponse.Loading -> {

                    }

                    is NetworkResponse.Success -> {
                        if (!it.data.data.isNullOrEmpty()) {
                            val currentCurrencyRate =
                                it.data.data?.map { currencyRate -> currencyRate.value }
                                    ?.find { currencyRate -> currencyRate.code == "USD" }
                                    ?: it.data.data?.map { currencyRate -> currencyRate.value }
                                        ?.first()
                            _uiState.update { state -> state.copy(currentCurrencyRate = currentCurrencyRate) }
                        }
                        val countryByCurrencyCode =
                            countries.associateBy { country -> country.currencyCode }
                        val map = it.data.data?.filter { currency ->
                            countryByCurrencyCode[currency.key] != null
                        }?.map { currencyRate ->
                            Triple(
                                null,
                                countryByCurrencyCode[currencyRate.key]?.image,
                                currencyRate.value,
                            )
                        }
                        _uiState.update { state ->
                            state.copy(
                                currencyRate = map?.sortedBy { map -> map.third.code },
                                exchangeRate = listOf()
                            )
                        }
                        updateShowLoadingDialog(false)
                    }

                    is NetworkResponse.Error -> {
                        updateShowLoadingDialog(false)
                    }
                }
            }
        }
    }

    fun calculateExchangeRate(value: Double) {
        val result = _uiState.value.currencyRate?.map {
            value.times(it.third?.value ?: 0.0).div(
                _uiState.value.currentCurrencyRate?.value ?: 0.0
            )
        }
        _uiState.update { state ->
            state.copy(currencyRate = _uiState.value.currencyRate?.mapIndexed { index, triple ->
                Triple(result?.get(index), triple.second, triple.third)
            })
        }
    }

    private fun updateShowLoadingDialog(boolean: Boolean) {
        _uiState.update { state -> state.copy(isShowDialog = boolean) }
    }

     fun updateShowPickerCurrencyDialog(boolean: Boolean) {
        _uiState.update { state -> state.copy(isShowPickerCurrencyDialog = boolean) }
    }

    fun setCurrentCurrencyRate(currencyRate: CurrencyRate?) {
        _uiState.update { state -> state.copy(currentCurrencyRate = currencyRate) }
    }
}