package ru.androidschool.intensiv

import android.app.Application
import androidx.viewbinding.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.androidschool.intensiv.di.dataModule
import ru.androidschool.intensiv.di.domainModule
import ru.androidschool.intensiv.di.presentationModule
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDebugTools()

        startKoin {
            androidContext(this@MovieFinderApp)
            modules(domainModule, presentationModule, dataModule)
        }
    }
    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: MovieFinderApp? = null
            private set
    }
}
