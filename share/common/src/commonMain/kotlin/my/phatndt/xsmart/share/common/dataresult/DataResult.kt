package my.phatndt.xsmart.share.common.dataresult

sealed interface DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>

    data class Failure(val error: Throwable) : DataResult<Nothing>

    fun getOrNull(): T? = when (this) {
        is Failure -> null
        is Success -> this.data
    }

    fun exceptionOrNull(): Throwable? = when (this) {
        is Failure -> this.error
        is Success -> null
    }

    fun isSuccess(): Boolean = this is Success

    fun isFailure(): Boolean = this is Failure
}
