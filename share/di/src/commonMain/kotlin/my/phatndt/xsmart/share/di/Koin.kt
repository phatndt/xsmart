package my.phatndt.xsmart.share.di

import my.phatndt.xsmart.share.di.data.dataModule
import my.phatndt.xsmart.share.di.domain.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(dataModule)
    modules(domainModule)
}
