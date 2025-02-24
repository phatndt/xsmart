package my.phatndt.xsmart.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import my.phatndt.xsmart.datastore.extension.createPreferencesDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun providerPreferencesDataStoreModule(): Module = module {
    single<DataStore<Preferences>> {
        createPreferencesDataStore()
    }
}
