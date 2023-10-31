package com.wallet.pools.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.wallet.pools.enum.TypeThemeApp
import javax.inject.Inject

open class CachePreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = " com.wallet.pools.preferences"

        private const val PREF_KEY_THE_FIRST_OPEN_APP = "the_first_open_app"
        private const val PREF_KEY_THE_THEME_APP = "the_theme_app"

    }

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var preferences = EncryptedSharedPreferences.create(
        context,
        PREF_PACKAGE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    var theFirstOpenApp: Boolean
        get() = preferences.getBoolean(PREF_KEY_THE_FIRST_OPEN_APP, true)
        set(isDarkMode) = preferences.edit().putBoolean(PREF_KEY_THE_FIRST_OPEN_APP, isDarkMode)
            .apply()


    var themeAppCurrent: Int
        get() = preferences.getInt(PREF_KEY_THE_THEME_APP, TypeThemeApp.THEME_AUTO.value)
        set(isTheme) = preferences.edit().putInt(PREF_KEY_THE_THEME_APP, isTheme)
            .apply()
}
