package my.phatndt.xsmart.datastore.helper

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import my.phatndt.xsmart.datastore.helper.base.BaseDataStore
import my.phatndt.xsmart.datastore.helper.base.BaseDataStoreHelper

internal class DataStoreHelper(
    private val dataStore: DataStore<Preferences>,
) : BaseDataStoreHelper, BaseDataStore {
    //<editor-fold desc="base func">
    override suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> = dataStore.data.map { preferences ->
        preferences[key] ?: defaultValue
    }
    //</editor-fold>

    //<editor-fold desc="int">
    override suspend fun setIntValue(key: String, value: Int) {
        setValue(intPreferencesKey(key), value)
    }

    override suspend fun getIntValue(key: String, defaultValue: Int) {
        getValue(intPreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="double">
    override suspend fun setDoubleValue(key: String, value: Double) {
        setValue(doublePreferencesKey(key), value)
    }

    override suspend fun getDoubleValue(key: String, defaultValue: Double) {
        getValue(doublePreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="string">
    override suspend fun setStringValue(key: String, value: String) {
        setValue(stringPreferencesKey(key), value)
    }

    override suspend fun getStringValue(key: String, defaultValue: String) {
        getValue(stringPreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="boolean">
    override suspend fun setBooleanValue(key: String, value: Boolean) {
        setValue(booleanPreferencesKey(key), value)
    }

    override suspend fun getBooleanValue(key: String, defaultValue: Boolean) {
        getValue(booleanPreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="float">
    override suspend fun setFloatValue(key: String, value: Float) {
        setValue(floatPreferencesKey(key), value)
    }

    override suspend fun getFloatValue(key: String, defaultValue: Float) {
        getValue(floatPreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="long">
    override suspend fun setLongValue(key: String, value: Long) {
        setValue(longPreferencesKey(key), value)
    }

    override suspend fun getLongValue(key: String, defaultValue: Long) {
        getValue(longPreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="string set">
    override suspend fun setStringSetValue(key: String, value: Set<String>) {
        setValue(stringSetPreferencesKey(key), value)
    }

    override suspend fun getStringSetValue(key: String, defaultValue: Set<String>) {
        getValue(stringSetPreferencesKey(key), defaultValue)
    }
    //</editor-fold>

    //<editor-fold desc="byte array">
    override suspend fun setByteArrayValue(key: String, value: ByteArray) {
        setValue(byteArrayPreferencesKey(key), value)
    }

    override suspend fun getByteArrayValue(key: String, defaultValue: ByteArray) {
        getValue(byteArrayPreferencesKey(key), defaultValue)
    }
    //</editor-fold>
}
