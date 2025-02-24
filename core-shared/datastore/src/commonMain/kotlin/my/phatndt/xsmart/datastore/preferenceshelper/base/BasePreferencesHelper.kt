package my.phatndt.xsmart.datastore.preferenceshelper.base

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import my.phatndt.xsmart.datastore.helper.base.BaseDataStore

abstract class BasePreferencesHelper<T>(
    private val baseDatastore: BaseDataStore,
    private val key: Preferences.Key<T>,
    private val defaultValue: T,
) {
    val value: Flow<T> = baseDatastore.getValue(key, defaultValue)

    suspend fun setValue(value: T) = baseDatastore.setValue(key, value)
}
