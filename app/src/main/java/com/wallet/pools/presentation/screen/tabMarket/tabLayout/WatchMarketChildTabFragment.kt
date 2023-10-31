package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airo.playground.base.PagingLoadStateAdapter
import com.wallet.pools.R
import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentWatchMarketChildTabBinding
import com.wallet.pools.extenstion.dpToPx
import com.wallet.pools.extenstion.hideKeyboard
import com.wallet.pools.presentation.screen.main.MainActivity
import com.wallet.pools.util.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class WatchMarketChildTabFragment :
    BaseFragment<FragmentWatchMarketChildTabBinding, BaseViewModel>() {


    override val viewModel: WatchMarketChildTabViewModel by viewModels()

    @Inject
    lateinit var marketAdapter: WatchMarketPagingAdapter
    override fun getViewBinding(): FragmentWatchMarketChildTabBinding =
        FragmentWatchMarketChildTabBinding.inflate(layoutInflater)

    override fun onBackFragment() {

    }
    private var searchJob: Job? = null
    private var isKeyboardShowing = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
        initPaging()
    }


    private fun initViewStateChange() {


        viewModel.typeSortLiveData.observe(requireActivity()) { enumValue ->
            // Handle the updated enum value here
            when (enumValue) {
                "desc" -> {

                    binding.apply {
                        tvStateSort.text=getString(R.string.str_sort_descending)
                        ivStateSort.setImageResource(R.drawable.ic_sort_desc)
                    }
                }

                else -> {
                    binding.apply {
                        tvStateSort.text=getString(R.string.str_sort_asc)
                        ivStateSort.setImageResource(R.drawable.ic_sort_asc)
                    }
                }
            }
        }
        viewModel.isSearchHaveData.observe(viewLifecycleOwner) { isChange ->
            Timber.d(" isSearchTipsHaveData: $isChange")
            binding.apply {
                emptyWatchMarker.visibility = if (isChange) View.GONE else View.VISIBLE
                rcvWatchMarket.visibility = if (isChange) View.VISIBLE else View.GONE
            }
        }


    }



    private fun initView() {
        binding.apply {
            rcvWatchMarket.apply {
                adapter = marketAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = marketAdapter.withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(marketAdapter),
                    footer = PagingLoadStateAdapter(marketAdapter)
                )

                marketAdapter.setItemClickListener {
                    requireActivity().hideKeyboard()

                    val bundle = bundleOf("data" to it)
                    findNavController().navigate(R.id.detailWatchMarketFragment, bundle)
                }
            }
            ivClearText.setSafeOnClickListener {
                edtSearchToken.setText("")

            }

            edtSearchToken.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch()
                }
                true
            }
            edtSearchToken.doAfterTextChanged {
                if(edtSearchToken.text.toString().isNotEmpty()){
                    ivClearText.visibility=View.VISIBLE
                }else{
                    ivClearText.visibility=View.INVISIBLE
                }
                viewModel.searchString.value = it.toString()

                searchJob?.cancel() // Cancel the previous search job if it exists
                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(2000) // Delay for 2 seconds
                    performSearch()
                }
            }

            llSort.setSafeOnClickListener {
                if (viewModel.typeSortLiveData.value == "asc") {
                    viewModel.typeSortLiveData.value = "desc"
                } else {
                    viewModel.typeSortLiveData.value = "asc"
                }
                performSearch()
            }


            rootWatchMarket.setOnClickListener { requireActivity().hideKeyboard() }

            rootWatchMarket.viewTreeObserver.addOnGlobalLayoutListener {   // detect to hide show bottomView when keyboard show
                try {
                    Timber.i("addOnGlobalLayoutListener")
                    val heightDiff =
                        rootWatchMarket.rootView.height -rootWatchMarket.height
                    if (heightDiff > dpToPx(requireContext(), 200) && !isKeyboardShowing) {
                        // Keyboard is showing, adjust BottomNavigationView position
                        isKeyboardShowing = true
                        Timber.i("isKeyboardShowing = true")
                        (requireActivity() as MainActivity).hideBottomView()
                        //  binding.bottomNavigationViewMain.translationY = heightDiff.toFloat()
                    } else if (heightDiff < dpToPx(requireContext(), 200) && isKeyboardShowing) {
                        // Keyboard is hidden, reset BottomNavigationView position
                        isKeyboardShowing = false
                        Timber.i("isKeyboardShowing = false")
                        (requireActivity() as MainActivity).showBottomView()
                        // binding.bottomNavigationViewMain.translationY = 0f
                    }
                } catch (_: Exception) {
                }
            }


        }


    }

    private fun performSearch() {
        marketAdapter.refresh()
        requireActivity().hideKeyboard()

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
                    .collect { binding.rcvWatchMarket.scrollToPosition(0) }


            }

        }
    }


}