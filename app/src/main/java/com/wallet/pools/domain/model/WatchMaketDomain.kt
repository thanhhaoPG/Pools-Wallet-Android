package com.wallet.pools.domain.model

data class WatchMaketDomain(
    val data: List<Daum>,
    val total: Long,
)

data class Daum(
    val id: Long,
    val icon: String,
    val miniChart: String,
    val name: String,
    val symbol: String,
    val slug: String,
    val totalSupply: Double,
    val platform: Platform,
    val cmcRank: Long,
    val createdAt: String,
    val updatedAt: String,
    val quote: Quote,
)

data class Platform(
    val id: Long?,
    val name: String?,
    val symbol: String?,
    val slug: String?,
    val tokenAddress: String?,
)

data class Quote(
    val price: Double,
    val volume24h: Double,
    val volumeChange24h: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange90d: Double,
    val marketCap: Double,
    val marketCapDominance: Double,
    val fullyDilutedMarketCap: Double,
    val updatedAt: String,
)