package com.wallet.pools.presentation.screen.detailWatchMarket

import androidx.lifecycle.viewModelScope
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.data.request.RequestDataChart
import com.wallet.pools.data.request.RequestDataChartData
import com.wallet.pools.data.usercase.DataChartUserCase
import com.wallet.pools.domain.model.ListDataChartDomain
import com.wallet.pools.util.CoroutineContextProvider
import com.wallet.pools.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

sealed class GetDataChartState {
    data class Loading(val isLoading: Boolean = false) : GetDataChartState()
    data class Error(var error: String = "", var statusCode: Int?) : GetDataChartState()
    data class Success(val data: ListDataChartDomain) : GetDataChartState()
}

@HiltViewModel
class DetailWatchMarketViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getDataChartUserCase: DataChartUserCase
    ) : BaseViewModel(contextProvider) {
    private val _getDataChartState =
        MutableStateFlow<GetDataChartState>(GetDataChartState.Loading(false))
    val getDataChartState = _getDataChartState.asStateFlow()


    fun getDataChart(id : Long) {
        launchCoroutineIO {
            getDataChartUserCase.execute(
                RequestDataChart(
                    data = RequestDataChartData(id = id)
                )
            ).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _getDataChartState.value =
                            GetDataChartState.Loading(
                                true
                            )
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            _getDataChartState.value =
                                GetDataChartState.Success(
                                    result.data
                                )
                        }
                    }
                    is Resource.Error -> {
                        _getDataChartState.value =
                            GetDataChartState.Error(
                                result.message!!,
                                result.statusCode
                            )
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}