package my.phatndt.xsmart.feature.currency.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import my.phatndt.xsmart.core.data.NetworkResponse
import my.phatndt.xsmart.feature.currency.data.datasource.RemoteApiDataSource
import my.phatndt.xsmart.feature.currency.data.model.CountryModel
import my.phatndt.xsmart.feature.currency.data.model.CurrencyModel
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val remoteApiDataSource: RemoteApiDataSource,
) : CurrencyRepository {
    private var countries = mutableListOf<CountryModel>()
    override suspend fun loadCityFromLocalFile(): List<CountryModel> {
        if (countries.isEmpty()) {
            val json = ""
            val result = Json.decodeFromString<List<CountryModel>>(json)
            countries = result.toMutableList()
        }
        return countries
    }

    override suspend fun getCurrencyInDate(): Flow<NetworkResponse<CurrencyModel>> = TODO()
}
