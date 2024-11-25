package my.phatndt.xsmart.feature.bmi.domain.repository

interface BmiRepository {
//    fun getListBmi(): Flow<NetworkResponse<List<Bmi>>>
    fun insertBmi(bmi: Double, time: String)
}
