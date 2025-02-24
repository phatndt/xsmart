package my.phatndt.xsmart.datastore.helper.base

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface BaseDataStore {
    suspend fun <T> setValue(key: Preferences.Key<T>, value: T)
    fun <T> getValue(key: Preferences.Key<T>, defaultValue: T): Flow<T>
}
