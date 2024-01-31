package dev.haqim.aboutmovie.android

import android.app.Application
import dev.haqim.aboutmovie.android.di.appModule
import dev.haqim.aboutmovie.di.getSharedModules
import org.koin.core.context.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { 
            modules(appModule + getSharedModules())
        }
    }
}