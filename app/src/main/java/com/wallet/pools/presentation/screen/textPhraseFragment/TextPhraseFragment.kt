package com.wallet.pools.presentation.screen.textPhraseFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentTextPhraseBinding
import com.wallet.pools.databinding.FragmentWebViewBinding
import com.wallet.pools.presentation.screen.main.MainActivity
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

    private val listFakeData = ArrayList<TextPhrase>()
    private var listEmptyData = ArrayList<TextPhrase>()
    private var positionAdd = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            llConfirm.setOnClickListener {
                val intent = Intent(context , MainActivity::class.java)
                requireContext().startActivity(intent)
            }
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

            listFakeData.add(TextPhrase("1",false))
            listFakeData.add(TextPhrase("2",false))
            listFakeData.add(TextPhrase("3",false))
            listFakeData.add(TextPhrase("4",false))
            listFakeData.add(TextPhrase("5",false))
            listFakeData.add(TextPhrase("6",false))
            listFakeData.add(TextPhrase("7",false))
            listFakeData.add(TextPhrase("8",false))
            listFakeData.add(TextPhrase("9",false))
            listFakeData.add(TextPhrase("10",false))
            listFakeData.add(TextPhrase("11",false))
            listFakeData.add(TextPhrase("12",false))
            fillTextPhraseAdapter.list  = listEmptyData
            chooseTextPhraseAdapter.list = listFakeData
        }

        chooseTextPhraseAdapter.setClickChooseTextListener {
            if(!it.isSelected){
                it.isSelected = true
                listEmptyData.removeAt(positionAdd)
                listEmptyData.add(positionAdd,it)
                fillTextPhraseAdapter.list = listEmptyData
                positionAdd++
                fillTextPhraseAdapter.notifyDataSetChanged()
            }

        }




    }


}