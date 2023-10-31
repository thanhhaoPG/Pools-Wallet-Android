package com.wallet.pools.presentation.screen.recovery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentRecoveryBinding
import com.wallet.pools.databinding.FragmentWebViewBinding
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.recovery.adapter.ListRecoveryAdapter
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import com.wallet.pools.presentation.screen.webView.WebViewFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecoveryFragment : BaseFragment<FragmentRecoveryBinding, BaseViewModel>() {


    override val viewModel: BaseViewModel by viewModels()
    override fun getViewBinding(): FragmentRecoveryBinding =
        FragmentRecoveryBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    @Inject
    lateinit var listRecoveryAdapter : ListRecoveryAdapter

    override fun onStart() {
//        (requireActivity() as MainActivity).hideBottomView()
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
        binding.apply {
            llNext.setOnClickListener {
                findNavController().navigate(R.id.textPhraseFragment)
            }
        }

        initRecyclerview()
    }

    private fun initRecyclerview(){
        binding.apply {
            rvRecoveryText.apply {
                adapter = listRecoveryAdapter
                layoutManager = GridLayoutManager(requireContext(),2)
            }
        }

        listRecoveryAdapter.list = listOf("","","","","","","","","","","","")
    }
}