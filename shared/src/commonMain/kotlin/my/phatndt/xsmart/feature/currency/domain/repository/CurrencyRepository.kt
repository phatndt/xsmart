package my.phatndt.xsmart.feature.currency.domain.repository

import my.phatndt.xsmart.feature.currency.data.model.Country

interface CurrencyRepository {
    suspend fun loadCityFromLocalFile(): List<Country>
}