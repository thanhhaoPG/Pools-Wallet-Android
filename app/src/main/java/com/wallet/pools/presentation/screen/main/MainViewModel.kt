package com.wallet.pools.presentation.screen.main

import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,

    ) : BaseViewModel(contextProvider) {


    override fun onCleared() {

        super.onCleared()
    }
}

