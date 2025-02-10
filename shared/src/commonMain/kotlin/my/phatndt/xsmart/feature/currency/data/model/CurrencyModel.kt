package my.phatndt.xsmart.feature.currency.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyModel(
    @SerialName("meta")
    val meta: Meta? = null,
    @SerialName("data")
    val data: Map<String, CurrencyRate>? = null,
)

@Serializable
data class CurrencyRate(
    @SerialName("code")
    val code: String? = null,
    @SerialName("value")
    val value: Double? = null,
)

@Serializable
data class Meta(
    @SerialName("last_updated_at")
    val lastUpdatedAt: String? = null,
)
