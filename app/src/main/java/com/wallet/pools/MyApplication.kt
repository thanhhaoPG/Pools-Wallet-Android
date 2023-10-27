package com.wallet.pools

import android.app.Application
import com.wallet.pools.util.ReleaseTree
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

        if (BuildConfig.DEBUG)
        {
            Timber.plant(Timber.DebugTree())
        }
        else
        {
            Timber.plant(ReleaseTree())
        }


    }


}