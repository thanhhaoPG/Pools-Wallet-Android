package com.wallet.pools.data.remote

import com.wallet.pools.data.remote.dto.ListDataChartDto
import com.wallet.pools.data.remote.dto.WatchMarketDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

//    @POST("auth/login-email")
//    suspend fun loginEmail(
//        @Body request: RequestLogin
//    ): BaseResponse<LoginDto>
    @GET("/pools-wallet/market")
    suspend fun getWatchMarket(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String,
        @Query("search") search: String,
    ): WatchMarketDto

    @GET("/pools-wallet/market/chart/{id}")
    suspend fun getDataChart(
        @Path("id") id: Long,
    ): ListDataChartDto


}