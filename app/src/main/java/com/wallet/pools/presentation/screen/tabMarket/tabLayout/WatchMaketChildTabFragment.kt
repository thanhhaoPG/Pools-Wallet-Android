package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airo.playground.base.PagingLoadStateAdapter
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentNewChildTabBinding
import com.wallet.pools.databinding.FragmentWatchMaketChildTabBinding
import com.wallet.pools.extenstion.showToast
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.presentation.screen.tabWallet.WalletViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class WatchMaketChildTabFragment : BaseFragment<FragmentWatchMaketChildTabBinding, BaseViewModel>() {


    override val viewModel: WatchMarketChildTabViewModel by viewModels()

    @Inject
    lateinit var marketAdapter: WatchMarketPagingAdapter
    override fun getViewBinding(): FragmentWatchMaketChildTabBinding =
        FragmentWatchMaketChildTabBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).showBottomView()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
        initPaging()
    }


    private fun initViewStateChange() {


    }

    private fun initView() {
        binding.apply {
            rvListWatchMarket.apply {
                adapter = marketAdapter
                layoutManager =LinearLayoutManager(context,RecyclerView.VERTICAL,false)
            }
        }

        binding.rvListWatchMarket.adapter = marketAdapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(marketAdapter),
            footer = PagingLoadStateAdapter(marketAdapter)
        )
        marketAdapter.setItemClickListener {
            requireContext().showToast(it.name)
        }
    }
    private fun initPaging() {
        viewModel.getListWatchMarket()
        lifecycleScope.launch {
            //  tipAdapter.loadStateFlow.collectLatest {  }

            with(viewModel) {
                tipPagingFlow.collectLatest { pagingData ->
                    marketAdapter.submitData(pagingData)
                }
                marketAdapter.loadStateFlow.collectLatest {
//                    binding.swipeRefreshMyTip.isRefreshing = it.refresh is LoadState.Loading
                }
                marketAdapter.loadStateFlow.distinctUntilChangedBy { it.refresh }
                    .filter { it.refresh is LoadState.NotLoading }
                    .collect { binding.rvListWatchMarket.scrollToPosition(0) }



            }

        }
    }


}