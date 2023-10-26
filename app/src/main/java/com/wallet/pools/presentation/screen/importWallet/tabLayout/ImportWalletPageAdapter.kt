package com.wallet.pools.presentation.screen.importWallet.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wallet.pools.presentation.screen.tabWallet.tabLayout.NFTsChildTabFragment
import com.wallet.pools.presentation.screen.tabWallet.tabLayout.WalletChildTabFragment


class ImportWalletPageAdapter(
    fragmentManager: FragmentActivity,
) : FragmentStateAdapter(fragmentManager) {


    private var childFragment: Array<Fragment>

    init {
        val introChildFragment1 = RecoveryPhraseChildTabFragment()

        val introChildFragment2 = PrivateKeyChildTabFragment()



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