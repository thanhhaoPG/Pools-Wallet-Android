package com.wallet.pools.presentation.screen.tabSetting

import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,


    ) : BaseViewModel(contextProvider) {


}