package com.wallet.pools.presentation.screen.tabWallet

import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WalletViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,


    ) : BaseViewModel(contextProvider) {


}