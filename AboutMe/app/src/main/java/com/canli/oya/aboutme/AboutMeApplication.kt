package com.canli.oya.aboutme

import android.app.Application
import timber.log.Timber

class AboutMeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}