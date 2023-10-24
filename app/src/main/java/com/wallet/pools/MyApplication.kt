package com.wallet.pools

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    companion object {

        lateinit var instance: MyApplication


    }


    override fun onCreate() {
        instance = this
        super.onCreate()

        Timber.plant(Timber.DebugTree())


    }


}