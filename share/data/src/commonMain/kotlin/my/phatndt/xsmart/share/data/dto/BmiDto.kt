package my.phatndt.xsmart.share.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BmiDto(
    val id: Long,
    val bmi: Double,
    val time: String,
)
