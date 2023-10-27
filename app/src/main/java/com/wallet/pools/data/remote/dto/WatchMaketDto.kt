package com.wallet.pools.data.remote.dto

import com.squareup.moshi.Json
import com.wallet.pools.domain.model.Daum
import com.wallet.pools.domain.model.Platform
import com.wallet.pools.domain.model.Quote
import com.wallet.pools.domain.model.WatchMaketDomain
import kotlin.math.min

class WatchMaketDto (
    @Json(name = "data")
    val data: List<DaumDto>,
    @Json(name = "total")
    val total: Long,
)
data class DaumDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "miniChart")
    val miniChart: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "totalSupply")
    val totalSupply: Double,
    @Json(name = "platform")
    val platform: PlatformDto,
    @Json(name = "cmcRank")
    val cmcRank: Long,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "quote")
    val quote: QuoteDto,
)

data class PlatformDto(
    @Json(name = "id")
    val id: Long?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "tokenAddress")
    val tokenAddress: String?,
)

data class QuoteDto(
    @Json(name = "price")
    val price: Double,
    @Json(name = "volume24h")
    val volume24h: Double,
    @Json(name = "volumeChange24h")
    val volumeChange24h: Double,
    @Json(name = "percentChange1h")
    val percentChange1h: Double,
    @Json(name = "percentChange24h")
    val percentChange24h: Double,
    @Json(name = "percentChange7d")
    val percentChange7d: Double,
    @Json(name = "percentChange30d")
    val percentChange30d: Double,
    @Json(name = "percentChange60d")
    val percentChange60d: Double,
    @Json(name = "percentChange90d")
    val percentChange90d: Double,
    @Json(name = "marketCap")
    val marketCap: Double,
    @Json(name = "marketCapDominance")
    val marketCapDominance: Double,
    @Json(name = "fullyDilutedMarketCap")
    val fullyDilutedMarketCap: Double,
    @Json(name = "updatedAt")
    val updatedAt: String,
)

fun WatchMaketDto.toDomain() : WatchMaketDomain {
    return WatchMaketDomain(
        data = this.data.map { it.toDomain() },
        total = this.total
    )
}
fun DaumDto.toDomain() : Daum{
    return  Daum(
        id = id,
        icon = icon,
        miniChart = miniChart,
        name = name,
        symbol = symbol,
        slug = slug,
        totalSupply = totalSupply,
        platform = platform.toDomain(),
        cmcRank = cmcRank,
        createdAt = createdAt,
        updatedAt = updatedAt,
        quote = quote.toDomain()
    )
}
fun PlatformDto.toDomain(): Platform{
    return Platform(
        id = id,
        name = name,
        symbol = symbol,
        slug = slug,
        tokenAddress = tokenAddress
    )
}
fun QuoteDto.toDomain():Quote{
    return Quote(
        price = price,
        volume24h = volume24h,
        volumeChange24h = volumeChange24h,
        percentChange1h = percentChange1h,
        percentChange24h = percentChange24h,
        percentChange7d = percentChange7d,
        percentChange30d= percentChange30d,
        percentChange60d =percentChange60d,
        percentChange90d = percentChange90d,
        marketCap = marketCap,
        marketCapDominance = marketCapDominance,
        fullyDilutedMarketCap = fullyDilutedMarketCap,
        updatedAt = updatedAt
    )
}


