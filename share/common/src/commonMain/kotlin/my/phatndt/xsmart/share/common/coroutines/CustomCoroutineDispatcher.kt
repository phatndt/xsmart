package my.phatndt.xsmart.share.common.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext

@OptIn(ExperimentalCoroutinesApi::class)
object CustomCoroutineDispatcher {
    private val backgroundDispatcher = newFixedThreadPoolContext(10, "App Background")
    val ioDispatcher = backgroundDispatcher.limitedParallelism(5)
    val defaultDispatcher = backgroundDispatcher.limitedParallelism(5)
}
