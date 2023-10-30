package com.wallet.pools.data.repository

import com.wallet.pools.data.remote.MyApi
import com.wallet.pools.data.remote.dto.toDomain
import com.wallet.pools.data.request.RequestDataChart
import com.wallet.pools.data.request.RequestWatchMaket
import com.wallet.pools.domain.model.ListDataChartDomain
import com.wallet.pools.domain.model.WatchMarketDomain
import com.wallet.pools.domain.repository.MyRepository
import com.wallet.pools.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyImpl @Inject constructor(
    val api: MyApi
) : MyRepository {

//    override suspend fun loginUser(request: RequestLogin): Flow<Resource<LoginDomain>> {
//        return flow {
//            emit(Resource.Loading())
//            val user = api.loginEmail(request).data?.toDomain()
//            user?.let { emit(Resource.Success(it)) }
//        }.catch {
//            this.handleError(it)
//        }
//    }



    override suspend fun getWatchMaket(requestWatchMaket: RequestWatchMaket): Flow<Resource<WatchMarketDomain>> {
        return flow {
            emit(Resource.Loading())
            val user = api.getWatchMaket(
                page = requestWatchMaket.data.page,
                limit = requestWatchMaket.data.limit,
                order = requestWatchMaket.data.order
            ).toDomain()
            user?.let { emit(Resource.Success(it)) }
        }.catch {
//            this.handleError(it)
        }
    }

    override suspend fun getDataChart(requestDataChart: RequestDataChart): Flow<Resource<ListDataChartDomain>> {
        return flow {
            emit(Resource.Loading())
            val user = api.getDataChart(
               id = requestDataChart.data.id
            ).toDomain()
            user?.let { emit(Resource.Success(it)) }
        }.catch {

        }
    }

}