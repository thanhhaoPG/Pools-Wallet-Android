package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.data.remote.MyApi
import com.wallet.pools.data.remote.dto.toDomain
import com.wallet.pools.domain.model.Daum
import com.wallet.pools.domain.model.WatchMaketDomain
import com.wallet.pools.util.Constant
import com.wallet.pools.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



@HiltViewModel
class WatchMarketChildTabViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val myApi: MyApi
    ) : BaseViewModel(contextProvider) {

    private lateinit var _tipPagingFlow: Flow<PagingData<Daum>>
    val tipPagingFlow: Flow<PagingData<Daum>>
        get() = _tipPagingFlow

    fun getListWatchMarket(): Flow<PagingData<Daum>> {

        _tipPagingFlow = Pager(PagingConfig(pageSize = Constant.LIMIT_TIP_IN_ONE_PAGE)) {
            MyTipPagingSource()
        }.flow.cachedIn(viewModelScope)

        return _tipPagingFlow
    }

    inner class MyTipPagingSource : PagingSource<Int, Daum>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Daum> {
            return try {

                val page = params.key ?: 1

                val response = myApi.getWatchMaket(
                    page = page,
                    limit = Constant.LIMIT_TIP_IN_ONE_PAGE,
                    order = "asc"
                )
                val data =
                    response.toDomain()
                val nextPage =
                    if (data == null || data.data.isEmpty() || data.total <= page) null else page + 1

                LoadResult.Page(
                    data = data.data ?: ArrayList(),
                    prevKey = null,
                    nextKey = nextPage
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }


        override fun getRefreshKey(state: PagingState<Int, Daum>): Int? {
            return null
        }

    }
}