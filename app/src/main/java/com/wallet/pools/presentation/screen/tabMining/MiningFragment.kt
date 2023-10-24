package com.wallet.pools.presentation.screen.tabMining

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTabMarketBinding
import com.wallet.pools.databinding.FragmentTabMiningBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MiningFragment : BaseFragment<FragmentTabMiningBinding, BaseViewModel>() {


    override val viewModel: MiningViewModel by viewModels()


    override fun getViewBinding(): FragmentTabMiningBinding =
        FragmentTabMiningBinding.inflate(layoutInflater)

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