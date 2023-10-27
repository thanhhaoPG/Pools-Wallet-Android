package com.wallet.pools.data.remote

import com.wallet.pools.data.remote.dto.WatchMaketDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MyApi {

//    @POST("auth/login-email")
//    suspend fun loginEmail(
//        @Body request: RequestLogin
//    ): BaseResponse<LoginDto>
    @GET("/pools-wallet/market")
    suspend fun getWatchMaket(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String,
    ): WatchMaketDto


}