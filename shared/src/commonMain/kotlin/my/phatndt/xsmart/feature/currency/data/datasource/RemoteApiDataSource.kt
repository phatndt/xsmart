package my.phatndt.xsmart.feature.currency.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import my.phatndt.xsmart.feature.currency.data.model.CurrencyModel
import my.phatndt.xsmart.feature.currency.data.model.Meta

class RemoteApiDataSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val httpClient: HttpClient,
) {
    suspend fun getCurrencyInDate(): CurrencyModel {
        return withContext(ioDispatcher) {
            httpClient.get("https://api.currencyapi.com/v3/latest") {
                url {
                    parameters.append(
                        "apikey",
                        "TNSg75BUpJJmrqKq3MbbQLCLv87Bs4xg4QlRZ1OL",
                    )
                }
            }.let {
                val result = Json.decodeFromString<CurrencyModel>(it.body())
                return@withContext result.copy(
                    meta = Meta(
                        lastUpdatedAt = result.meta?.lastUpdatedAt?.let { date ->
                            Instant.parse(
                                date,
                            ).toEpochMilliseconds().toString()
                        },
                    ),
                )
            }
        }
    }
}
