package com.wallet.pools.presentation.screen.tabSetting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTabMarketBinding
import com.wallet.pools.databinding.FragmentTabSettingBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentTabSettingBinding, BaseViewModel>() {


    override val viewModel: SettingViewModel by viewModels()


    override fun getViewBinding(): FragmentTabSettingBinding =
        FragmentTabSettingBinding.inflate(layoutInflater)

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