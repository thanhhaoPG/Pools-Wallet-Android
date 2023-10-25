package com.wallet.pools.presentation.screen.tabWallet.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WalletViewPagetAdapter(
    fragmentManager: FragmentActivity,
) : FragmentStateAdapter(fragmentManager) {


    private var childFragment: Array<Fragment>

    init {
        val introChildFragment1 = WalletChildTabFragment()

        val introChildFragment2 = NFTsChildTabFragment()



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