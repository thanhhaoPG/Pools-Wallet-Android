package com.wallet.pools.presentation.screen.settingTheme

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.enum.TypeThemeApp
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingThemeViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
) : BaseViewModel(contextProvider) {

     fun setTheme(context: Context?, theme: Int) {
        updateTheme(context!!, theme)
    }

    private fun updateTheme(context: Context, theme: Int) {
        if (theme == TypeThemeApp.THEME_LIGHT.value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else if (theme == TypeThemeApp.THEME_DARK.value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            val mode = uiModeManager.nightMode
            if (mode == UiModeManager.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else if (mode == UiModeManager.MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }



}