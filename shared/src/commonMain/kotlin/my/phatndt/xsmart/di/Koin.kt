package my.phatndt.xsmart.di

import my.phatndt.xsmart.core.data.CustomCoroutineDispatcher
import my.phatndt.xsmart.core.shared.ResourceReader
import my.phatndt.xsmart.feature.bmi.data.repository.BmiRepositoryImpl
import my.phatndt.xsmart.feature.bmi.domain.repository.BmiRepository
import my.phatndt.xsmart.feature.currency.data.repository.CurrencyRepositoryImpl
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        dataModule,
    )
}

val dataModule = module {
    single(named("ioDispatcher")) { CustomCoroutineDispatcher.ioDispatcher }
    single(named("defaultDispatcher")) { CustomCoroutineDispatcher.defaultDispatcher }
    single<BmiRepository> { BmiRepositoryImpl(get(), get(qualifier = named("ioDispatcher"))) }
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            get(),
            get(qualifier = named("defaultDispatcher"))
        )
    }
}