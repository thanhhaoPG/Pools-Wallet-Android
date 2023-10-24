package com.wallet.pools.presentation.screen.tabMining

import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MiningViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,


    ) : BaseViewModel(contextProvider) {


}