package my.phatndt.xsmart.android

import android.app.Application
import my.phatndt.xsmart.android.core.di.androidModule
import my.phatndt.xsmart.share.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
            modules(androidModule)
        }
    }
}
