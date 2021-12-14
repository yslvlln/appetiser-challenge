package com.challenge.itunes

import android.app.Application
import com.challenge.itunes.di.dbModule
import com.challenge.itunes.di.networkModule
import com.challenge.itunes.di.repoModule
import com.challenge.itunes.di.vmModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ItunesApplication: Application() {

    private val koinModules = listOf(repoModule, vmModule, networkModule, dbModule)

    override fun onCreate() {
        super.onCreate()
        //Setup koin
        startKoin {
            androidContext(applicationContext)
            modules(koinModules)
        }

        //Setup stetho
        Stetho.initializeWithDefaults(this)
    }

}