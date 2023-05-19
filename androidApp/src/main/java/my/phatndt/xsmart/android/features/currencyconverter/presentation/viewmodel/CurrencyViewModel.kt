package my.phatndt.xsmart.android.features.currencyconverter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.phatndt.xsmart.feature.currency.data.model.Country
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository

class CurrencyViewModel(private val currencyRepository: CurrencyRepository): ViewModel() {
    var countries = mutableListOf<Country>()
    init {
        loadCountries()
    }
    private fun loadCountries() {
        viewModelScope.launch {
            countries = currencyRepository.loadCityFromLocalFile().toMutableList()
        }
    }
}