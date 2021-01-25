package com.example.navigationtestapp

import android.app.Application
import com.example.navigationtestapp.repositiory.PersistentSettings
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestAppApplication : Application() {

    private val persistentSettings: PersistentSettings by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestAppApplication)
            modules(appModule)
        }

        MainScope().launch {
            persistentSettings.incrementSession()
        }
    }
}