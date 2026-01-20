package my.phatndt.xsmart.share.common.dataresult

fun <T> DataResult<T>.onSuccess(callback: (T) -> Unit) {
    if (this is DataResult.Success) {
        callback(this.data)
    }
}

fun <T> DataResult<T>.getOrNull(): T? {
    if (this is DataResult.Success) {
        return this.data
    }
    return null
}

