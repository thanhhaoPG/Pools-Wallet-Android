package com.wallet.pools.presentation.screen.importWallet.tabLayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentPrivateKeyChildTabBinding
import com.wallet.pools.databinding.FragmentRecoveryPhraseChildTabBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class RecoveryPhraseChildTabFragment : BaseFragment<FragmentRecoveryPhraseChildTabBinding, BaseViewModel>() {


    override val viewModel: WalletViewModel by viewModels()


    override fun getViewBinding(): FragmentRecoveryPhraseChildTabBinding =
        FragmentRecoveryPhraseChildTabBinding.inflate(layoutInflater)

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


    }


}