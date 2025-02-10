package my.phatndt.xsmart.feature.currency.domain.repository

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.core.data.NetworkResponse
import my.phatndt.xsmart.feature.currency.data.model.CountryModel
import my.phatndt.xsmart.feature.currency.data.model.CurrencyModel

interface CurrencyRepository {
    suspend fun loadCityFromLocalFile(): List<CountryModel>
    suspend fun getCurrencyInDate(): Flow<NetworkResponse<CurrencyModel>>
}
