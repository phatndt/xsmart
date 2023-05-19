package my.phatndt.xsmart.feature.currency.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import my.phatndt.xsmart.core.shared.ResourceReader
import my.phatndt.xsmart.feature.currency.data.model.Country
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val resourceReader: ResourceReader,
    private val defaultDispatcher: CoroutineDispatcher,
): CurrencyRepository {
    private var countries = mutableListOf<Country>()
    override suspend fun loadCityFromLocalFile(): List<Country> {
        if (countries.isEmpty()) {
            val json = resourceReader.readResource("countries.json")
            val result = Json.decodeFromString<List<Country>>(json)
            countries = result.toMutableList()
        }
        return countries
    }
}