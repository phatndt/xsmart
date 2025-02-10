package my.phatndt.xsmart.android

import android.app.Application
import my.phatndt.xsmart.android.core.di.androidModule
import my.phatndt.xsmart.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
            modules(androidModule)
        }
    }
}
