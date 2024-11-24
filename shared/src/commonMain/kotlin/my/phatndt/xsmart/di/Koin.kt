package my.phatndt.xsmart.di

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import my.phatndt.xsmart.core.data.CustomCoroutineDispatcher
import my.phatndt.xsmart.feature.bmi.data.repository.BmiRepositoryImpl
import my.phatndt.xsmart.feature.bmi.domain.repository.BmiRepository
import my.phatndt.xsmart.feature.currency.data.datasource.RemoteApiDataSource
import my.phatndt.xsmart.feature.currency.data.datasource.RemoteFirebaseDataSource
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
    single {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
    single { RemoteApiDataSource(get(qualifier = named("ioDispatcher")), get()) }
    single { RemoteFirebaseDataSource() }
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
             get(qualifier = named("ioDispatcher")),
            get(qualifier = named("defaultDispatcher")), get(), get()
        )
    }
}
