package my.phatndt.xsmart.feature.bmi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BmiModel(
    val id: Long,
    val bmi: Double,
    val time: String,
)