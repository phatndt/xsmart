package my.phatndt.xsmart.feature.currency.data.repository

import io.ktor.client.plugins.logging.Logging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import my.phatndt.xsmart.core.data.NetworkResponse
import my.phatndt.xsmart.feature.currency.data.datasource.RemoteApiDataSource
import my.phatndt.xsmart.feature.currency.data.datasource.RemoteFirebaseDataSource
import my.phatndt.xsmart.feature.currency.data.model.CountryModel
import my.phatndt.xsmart.feature.currency.data.model.CurrencyModel
import my.phatndt.xsmart.feature.currency.data.model.Meta
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val remoteApiDataSource: RemoteApiDataSource,
    private val remoteFirebaseDataSource: RemoteFirebaseDataSource,
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

    override suspend fun getCurrencyInDate(): Flow<NetworkResponse<CurrencyModel>> = flow {
        emit(NetworkResponse.Loading)
        val result = remoteFirebaseDataSource.getCurrencyInDate()
        result.meta?.lastUpdatedAt?.let {
            val temp =
                Clock.System.now().toLocalDateTime(TimeZone.UTC).date.minus(DatePeriod(0, 0, 1))
            if (Instant.fromEpochMilliseconds(it.toLong())
                    .toLocalDateTime(TimeZone.UTC).date == temp
            ) {
                emit(NetworkResponse.Success(result))
            } else {
                val newCurrencyInDate = remoteApiDataSource.getCurrencyInDate()
                remoteFirebaseDataSource.uploadCurrencyDataToFireStore(newCurrencyInDate)
                emit(NetworkResponse.Success(newCurrencyInDate))
            }
        }
    }.catch {
        emit(NetworkResponse.Error(it))
    }
}
