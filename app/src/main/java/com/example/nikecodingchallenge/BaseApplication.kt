package com.example.nikecodingchallenge

import android.app.Application
import com.example.nikecodingchallenge.di.networkModule
import com.example.nikecodingchallenge.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    private val appModules = listOf(networkModule, vmModule)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(appModules)
        }
    }


}