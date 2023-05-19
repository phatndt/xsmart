package my.phatndt.xsmart.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import my.phatndt.xsmart.cache.XSmartDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(XSmartDatabase.Schema, "XSmartDatabase.db")
    }
}