package com.wallet.pools.presentation.screen.webView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.google.android.material.tabs.TabLayoutMediator
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTabWalletBinding
import com.wallet.pools.databinding.FragmentWebViewBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import com.wallet.pools.presentation.screen.tabWallet.tabLayout.WalletViewPagetAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding, BaseViewModel>() {


    override val viewModel: WalletViewModel by viewModels()
    private val args :WebViewFragmentArgs by navArgs()
    override fun getViewBinding(): FragmentWebViewBinding =
        FragmentWebViewBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            customWebView.setData(args.linkURL,args.nameTitle)

        }



    }


}