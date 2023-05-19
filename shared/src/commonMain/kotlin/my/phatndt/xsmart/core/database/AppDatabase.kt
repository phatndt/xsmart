package my.phatndt.xsmart.core.database

import my.phatndt.xsmart.cache.XSmartDatabase

class AppDatabase(private val databaseDriverFactory: DatabaseDriverFactory) {
    fun createDriver(): XSmartDatabase {
        return XSmartDatabase(
            driver = databaseDriverFactory.createDriver()
        )
    }
}