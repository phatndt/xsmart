package my.phatndt.xsmart.data.repo.bmi

import kotlinx.coroutines.CoroutineDispatcher
import my.phatndt.xsmart.domain.repo.bmi.BmiRepository

class BmiRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
) : BmiRepository {
//    override fun getListBmi(): Flow<NetworkResponse<List<Bmi>>> = flow {
//        emit(NetworkResponse.Loading)
//        val queries = database.xSmartDatabaseQueries
//        val result = queries.getListBmi().executeAsList()
//        emit(NetworkResponse.Success(emptyList<Bmi>()))
//    }.catch {
//        emit(NetworkResponse.Error(it))
//    }.flowOn(ioDispatcher)

    override fun insertBmi(bmi: Double, time: String) {
//        val queries = database.xSmartDatabaseQueries
//        queries.insertBmi(bmi, time)
    }
}
