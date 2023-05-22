package my.phatndt.xsmart.feature.currency.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CountryModel(
    val capital: String,
    val continentName: String,
    val countryCode: String,
    val countryName: String,
    val currencyCode: String,
    val image: String,
    val population: String
)