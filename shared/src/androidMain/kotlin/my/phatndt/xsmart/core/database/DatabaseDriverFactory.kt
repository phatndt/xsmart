package my.phatndt.xsmart.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import my.phatndt.xsmart.cache.XSmartDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(XSmartDatabase.Schema, context, "XSmartDatabase.db")
    }
}