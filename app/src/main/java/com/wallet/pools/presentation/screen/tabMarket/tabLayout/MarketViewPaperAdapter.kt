package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MarketViewPaperAdapter(
    fragmentManager: FragmentActivity,
) : FragmentStateAdapter(fragmentManager) {


    private var childFragment: Array<Fragment>

    init {
        val introChildFragment1 = WatchMaketChildTabFragment()

        val introChildFragment2 = NewChildTabFragment()



        childFragment = arrayOf(
            introChildFragment1,
            introChildFragment2,
        )
    }

    override fun getItemCount(): Int {
        return childFragment.size
    }

    override fun createFragment(position: Int): Fragment {

        return childFragment[position]


    }

}