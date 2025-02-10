package my.phatndt.xsmart.di

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import my.phatndt.xsmart.core.data.CustomCoroutineDispatcher
import my.phatndt.xsmart.data.VnSalaryCalculatorRepoImpl
import my.phatndt.xsmart.domain.repo.VnSalaryCalculatorRepo
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCase
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCaseImpl
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCaseImpl
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCaseImpl
import my.phatndt.xsmart.feature.bmi.data.repository.BmiRepositoryImpl
import my.phatndt.xsmart.feature.bmi.domain.repository.BmiRepository
import my.phatndt.xsmart.feature.currency.data.datasource.RemoteApiDataSource
import my.phatndt.xsmart.feature.currency.data.repository.CurrencyRepositoryImpl
import my.phatndt.xsmart.feature.currency.domain.repository.CurrencyRepository
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        dataModule,
        domainModule,
    )
}

val dataModule = module {
    single(named("ioDispatcher")) { CustomCoroutineDispatcher.ioDispatcher }
    single(named("defaultDispatcher")) { CustomCoroutineDispatcher.defaultDispatcher }
    single<BmiRepository> { BmiRepositoryImpl(get(qualifier = named("ioDispatcher"))) }
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
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            get(qualifier = named("ioDispatcher")),
            get(qualifier = named("defaultDispatcher")),
            get(),
        )
    }

    single { VnSalaryCalculatorRepoImpl() } bind VnSalaryCalculatorRepo::class
}

val domainModule = module {
    single {
        CalculateVnSalaryUseCaseImpl()
    } bind CalculateVnSalaryUseCase::class

    single {
        GetCalculateVnSalaryResultUseCaseImpl(get())
    } bind GetCalculateVnSalaryResultUseCase::class

    single {
        SaveCalculateVnSalaryResultUseCaseImpl(get())
    } bind SaveCalculateVnSalaryResultUseCase::class
}
