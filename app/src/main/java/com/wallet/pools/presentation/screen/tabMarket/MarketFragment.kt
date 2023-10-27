package com.wallet.pools.presentation.screen.tabMarket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTabMarketBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.tabMarket.tabLayout.MarketViewPaperAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MarketFragment : BaseFragment<FragmentTabMarketBinding, BaseViewModel>() {


    override val viewModel: MarketViewModel by viewModels()


    override fun getViewBinding(): FragmentTabMarketBinding =
        FragmentTabMarketBinding.inflate(layoutInflater)

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
        binding.apply {
            viewPaperMarket.adapter = MarketViewPaperAdapter(requireActivity())
            TabLayoutMediator(tabLayoutMarket,viewPaperMarket){ tab , position ->
                tab.text = when(position){
                    0->"Watch Market"
                    1->"New"
                    else -> ""
                }
            }.attach()
        }

    }


}