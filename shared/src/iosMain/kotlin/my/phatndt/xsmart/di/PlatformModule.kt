package my.phatndt.xsmart.di

import my.phatndt.xsmart.core.database.AppDatabase
import my.phatndt.xsmart.core.database.DatabaseDriverFactory
import my.phatndt.xsmart.core.shared.ResourceReader
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
    single { AppDatabase(get()).createDriver() }
    single { ResourceReader() }
}