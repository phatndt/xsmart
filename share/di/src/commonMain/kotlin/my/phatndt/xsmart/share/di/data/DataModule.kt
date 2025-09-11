package my.phatndt.xsmart.share.di.data

import my.phatndt.xsmart.share.common.coroutines.CustomCoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single(named("ioDispatcher")) { CustomCoroutineDispatcher.ioDispatcher }
    single(named("defaultDispatcher")) { CustomCoroutineDispatcher.defaultDispatcher }
} + repoModule
