package com.wallet.pools.data.request

data class RequestWatchMaket (
    val data :RequestWatchMaketData
)
data class RequestWatchMaketData(
    val page :Int,
    val limit:Int,
    val order:String
)