package com.wallet.pools.presentation.screen.textPhraseFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTextPhraseBinding
import com.wallet.pools.databinding.FragmentWebViewBinding
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import com.wallet.pools.presentation.screen.textPhraseFragment.adapter.ChooseTextPhraseAdapter
import com.wallet.pools.presentation.screen.textPhraseFragment.adapter.FillTextPhraseAdapter
import com.wallet.pools.presentation.screen.textPhraseFragment.model.TextPhrase
import com.wallet.pools.presentation.screen.webView.WebViewFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TextPhraseFragment : BaseFragment<FragmentTextPhraseBinding, BaseViewModel>() {


    override val viewModel: WalletViewModel by viewModels()
    private val args : WebViewFragmentArgs by navArgs()
    override fun getViewBinding(): FragmentTextPhraseBinding =
        FragmentTextPhraseBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    @Inject
    lateinit var fillTextPhraseAdapter: FillTextPhraseAdapter

    @Inject
    lateinit var chooseTextPhraseAdapter: ChooseTextPhraseAdapter

    override fun onStart() {
        super.onStart()

    }

    val listFakeData = ArrayList<TextPhrase>()
    val listEmptyData = ArrayList<TextPhrase>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            rvTextPhrase.apply {
                adapter = fillTextPhraseAdapter
                layoutManager = GridLayoutManager(context,2)
            }
            rvTextChoose.apply {
                adapter = chooseTextPhraseAdapter
                layoutManager = GridLayoutManager(context,3)
            }
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))
            listEmptyData.add(TextPhrase("",false))

            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            listFakeData.add(TextPhrase("",false))
            fillTextPhraseAdapter.list  = listEmptyData
            chooseTextPhraseAdapter.list = listFakeData
        }



    }


}