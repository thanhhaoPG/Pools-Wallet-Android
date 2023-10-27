package com.wallet.pools.domain.repository

import com.wallet.pools.data.request.RequestWatchMaket
import com.wallet.pools.domain.model.WatchMaketDomain
import com.wallet.pools.util.Resource
import kotlinx.coroutines.flow.Flow

interface MyRepository {

//    suspend fun loginUser(
//        request: RequestLogin
//    ): Flow<Resource<LoginDomain>>
    suspend fun getWatchMaket(requestWatchMaket: RequestWatchMaket) : Flow<Resource<WatchMaketDomain>>
}