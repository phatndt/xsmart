package my.phatndt.xsmart.feature.bmi.domain.repository

import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.cache.Bmi
import my.phatndt.xsmart.core.data.NetworkResponse

interface BmiRepository {
    fun getListBmi(): Flow<NetworkResponse<List<Bmi>>>
    fun insertBmi(bmi: Double, time: String)
}