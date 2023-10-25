package com.wallet.pools.presentation.screen.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTabMarketBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentTabMarketBinding, BaseViewModel>() {


    override val viewModel: LoginViewModel by viewModels()


    override fun getViewBinding(): FragmentTabMarketBinding =
        FragmentTabMarketBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        requireActivity().finish()

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