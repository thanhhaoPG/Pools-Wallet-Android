package com.wallet.pools.presentation.screen.login


import android.os.Bundle

import android.view.View
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentLoginBinding

import dagger.hilt.android.AndroidEntryPoint


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