package my.phatndt.xsmart.feature.bmi.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import my.phatndt.xsmart.cache.Bmi
import my.phatndt.xsmart.cache.XSmartDatabase
import my.phatndt.xsmart.core.data.NetworkResponse
import my.phatndt.xsmart.feature.bmi.domain.repository.BmiRepository

class BmiRepositoryImpl(
    private val database: XSmartDatabase,
    private val ioDispatcher: CoroutineDispatcher,
) : BmiRepository {
    override fun getListBmi(): Flow<NetworkResponse<List<Bmi>>> = flow {
        emit(NetworkResponse.Loading)
        val queries = database.xSmartDatabaseQueries
        val result = queries.getListBmi().executeAsList()
        emit(NetworkResponse.Success(result))
    }.catch {
        emit(NetworkResponse.Error(it))
    }.flowOn(ioDispatcher)

    override fun insertBmi(bmi: Double, time: String) {
        val queries = database.xSmartDatabaseQueries
        queries.insertBmi(bmi, time)
    }
}