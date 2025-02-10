package my.phatndt.xsmart.core.shared

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.contracts.ExperimentalContracts

@OptIn(ExperimentalContracts::class)
suspend fun <T> Flow<DataResult<T>>.collectFold(
    onError: (Throwable) -> Unit,
    onSuccess: FlowCollector<T>,
) {
    this.collect { result ->
        when (result) {
            is DataResult.Success -> onSuccess.emit(result.data)

            is DataResult.Failure -> onError(result.error)
        }
    }
}
