package com.wallet.pools

import android.app.Application
import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.wallet.pools.data.local.CachePreferencesHelper
import com.wallet.pools.enum.TypeThemeApp
import com.wallet.pools.util.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    companion object {

        lateinit var instance: MyApplication


    }

    @Inject
    lateinit var cachePreferencesHelper: CachePreferencesHelper

    override fun onCreate() {
        instance = this
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }


        //set theme app
        val defaultTheme: Int = cachePreferencesHelper.themeAppCurrent
        if (defaultTheme == TypeThemeApp.THEME_LIGHT.value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else if (defaultTheme == TypeThemeApp.THEME_DARK.value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            val uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
            val mode = uiModeManager.nightMode
            if (mode == UiModeManager.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else if (mode == UiModeManager.MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }


}