package my.phatndt.xsmart.datastore.preferenceshelper

import androidx.datastore.preferences.core.Preferences
import my.phatndt.xsmart.datastore.helper.base.BaseDataStore
import my.phatndt.xsmart.datastore.preferenceshelper.base.BasePreferencesHelper

class IntPreferencesHelper(
    baseDataStore: BaseDataStore,
    key: Preferences.Key<Int>,
    defaultValue: Int,
) : BasePreferencesHelper<Int>(baseDataStore, key, defaultValue)
