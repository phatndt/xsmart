package my.phatndt.xsmart.datastore.di

import my.phatndt.xsmart.datastore.helper.base.BaseDataStore
import my.phatndt.xsmart.datastore.helper.base.BaseDataStoreHelper
import my.phatndt.xsmart.datastore.helper.DataStoreHelper
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module
import androidx.datastore.core.DataStore

/**
 * Function provide preferences data store module.
 *
 */
internal expect fun providerPreferencesDataStoreModule(): Module

/**
 * The variable provides [DataStoreHelper] to save and get value.
 *
 * **Important**: we need to provide [DataStore] instance for [DataStoreHelper] by using
 * [preferencesDataStoreModule] variable or ..
 */
val datastoreHelperModule = module {
    single {
        DataStoreHelper(get())
    }.binds(arrayOf(BaseDataStoreHelper::class, BaseDataStore::class))
}

val preferencesDataStoreModule = providerPreferencesDataStoreModule()

