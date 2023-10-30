package com.wallet.pools.presentation.screen.importWallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel

import com.wallet.pools.databinding.FragmentImportWalletBinding
import com.wallet.pools.presentation.screen.importWallet.tabLayout.ImportWalletPageAdapter
import com.wallet.pools.presentation.screen.login.LoginViewModel

import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ImportWalletFragment : BaseFragment<FragmentImportWalletBinding, BaseViewModel>() {


    override val viewModel: LoginViewModel by viewModels()


    override fun getViewBinding(): FragmentImportWalletBinding =
        FragmentImportWalletBinding.inflate(layoutInflater)

    override fun onBackFragment() {
        findNavController().navigateUp()
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
            frmBack.setOnClickListener {
                onBackFragment()
            }
            viewPaperImportWallet.adapter = ImportWalletPageAdapter(requireActivity())
            TabLayoutMediator(tabLayoutImportWallet,viewPaperImportWallet){ tab , position ->
                tab.text = when(position){
                    0->"Recovery Phrase"
                    1->"Private key"
                    else -> ""
                }
            }.attach()

            ivScanQR.setOnClickListener {
                findNavController().navigate(R.id.scanQRFragment)
            }
        }

    }
}