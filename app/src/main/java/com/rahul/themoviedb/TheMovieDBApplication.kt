package com.rahul.themoviedb

import android.app.Application
import com.rahul.themoviedb.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheMovieDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin{
            androidContext(this@TheMovieDBApplication)
            modules(appModule) // Add your Koin modules here
        }
    }
}