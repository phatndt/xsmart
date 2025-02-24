package my.phatndt.xsmart.datastore.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal fun createPreferencesDataStore(context: Context): DataStore<Preferences> = createPreferencesDataStore {
    context.filesDir.resolve(dataStoreFileName).absolutePath
}
