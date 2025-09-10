package my.phatndt.xsmart.share.domain.repo.bmi

interface BmiRepository {
//    fun getListBmi(): Flow<NetworkResponse<List<Bmi>>>
    fun insertBmi(bmi: Double, time: String)
}
