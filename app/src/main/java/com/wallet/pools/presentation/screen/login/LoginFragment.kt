package com.wallet.pools.presentation.screen.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentLoginBinding
import com.wallet.pools.databinding.FragmentTabMarketBinding
import com.wallet.pools.presentation.screen.createNewWallet.CreatNewWalletFragment
import com.wallet.pools.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import timber.log.Timber


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, BaseViewModel>() {


    override val viewModel: LoginViewModel by viewModels()


    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

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
        binding.apply {
            llCreateWallet.setOnClickListener {
                findNavController().navigate(R.id.creatNewWalletFragment)
            }
            tvImportWallet.setOnClickListener {
                findNavController().navigate(R.id.importWalletFragment)
            }
        }

    }



}