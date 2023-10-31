package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentNewChildTabBinding
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsChildTabFragment : BaseFragment<FragmentNewChildTabBinding, BaseViewModel>() {


    override val viewModel: WalletViewModel by viewModels()


    override fun getViewBinding(): FragmentNewChildTabBinding =
        FragmentNewChildTabBinding.inflate(layoutInflater)

    override fun onBackFragment() {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {


    }


}