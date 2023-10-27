package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentNewChildTabBinding
import com.wallet.pools.databinding.FragmentNftsChildTabBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewChildTabFragment : BaseFragment<FragmentNewChildTabBinding, BaseViewModel>() {


    override val viewModel: WalletViewModel by viewModels()


    override fun getViewBinding(): FragmentNewChildTabBinding =
        FragmentNewChildTabBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        requireActivity().finish()

    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).showBottomView()

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