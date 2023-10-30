package com.wallet.pools.data.remote.dto

import com.squareup.moshi.Json
import com.wallet.pools.domain.model.Daum
import com.wallet.pools.domain.model.Platform
import com.wallet.pools.domain.model.Quote
import com.wallet.pools.domain.model.WatchMarketDomain

class WatchMarketDto (
    @Json(name = "data")
    val data: List<DaumDto>,
    @Json(name = "total")
    val total: Long,
)
data class DaumDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "miniChart")
    val miniChart: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "totalSupply")
    val totalSupply: Double?,
    @Json(name = "platform")
    val platform: PlatformDto?,
    @Json(name = "cmcRank")
    val cmcRank: Long?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
    @Json(name = "quote")
    val quote: QuoteDto?,
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
    val price: Double?,
    @Json(name = "volume24h")
    val volume24h: Double?,
    @Json(name = "volumeChange24h")
    val volumeChange24h: Double?,
    @Json(name = "percentChange1h")
    val percentChange1h: Double?,
    @Json(name = "percentChange24h")
    val percentChange24h: Double?,
    @Json(name = "percentChange7d")
    val percentChange7d: Double?,
    @Json(name = "percentChange30d")
    val percentChange30d: Double?,
    @Json(name = "percentChange60d")
    val percentChange60d: Double?,
    @Json(name = "percentChange90d")
    val percentChange90d: Double?,
    @Json(name = "marketCap")
    val marketCap: Double?,
    @Json(name = "marketCapDominance")
    val marketCapDominance: Double?,
    @Json(name = "fullyDilutedMarketCap")
    val fullyDilutedMarketCap: Double?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
)

fun WatchMarketDto.toDomain() : WatchMarketDomain {
    return WatchMarketDomain(
        data = this.data.map { it.toDomain() },
        total = this.total
    )
}
fun DaumDto.toDomain() : Daum{
    return  Daum(
        id = id,
        icon = icon ?:"",
        miniChart = miniChart?:"",
        name = name?:"",
        symbol = symbol?:"",
        slug = slug?:"",
        totalSupply = totalSupply ?:0.0,
        platform = (this.platform ?: PlatformDto(
            id = 0,
            name = "",
            symbol = "",
            slug = "",
            tokenAddress = ""
        )).toDomain(),
        cmcRank = cmcRank ?:0,
        createdAt = createdAt?:"",
        updatedAt = updatedAt?:"",
        quote = (this.quote ?: QuoteDto(
            price = 0.0,
            volume24h = 0.0,
            volumeChange24h = 0.0,
            percentChange1h = 0.0,
            percentChange24h = 0.0,
            percentChange7d = 0.0,
            percentChange30d= 0.0,
            percentChange60d =0.0,
            percentChange90d = 0.0,
            marketCap = 0.0,
            marketCapDominance = 0.0,
            fullyDilutedMarketCap = 0.0,
            updatedAt = ""
        )).toDomain()
    )
}
fun PlatformDto.toDomain(): Platform{
    return Platform(
        id = id?:0,
        name = name?:"",
        symbol = symbol?:"",
        slug = slug?:"",
        tokenAddress = tokenAddress?:""
    )
}
fun QuoteDto.toDomain():Quote{
    return Quote(
        price = price?:0.0,
        volume24h = volume24h?:0.0,
        volumeChange24h = volumeChange24h?:0.0,
        percentChange1h = percentChange1h?:0.0,
        percentChange24h = percentChange24h?:0.0,
        percentChange7d = percentChange7d?:0.0,
        percentChange30d= percentChange30d?:0.0,
        percentChange60d =percentChange60d?:0.0,
        percentChange90d = percentChange90d?:0.0,
        marketCap = marketCap?:0.0,
        marketCapDominance = marketCapDominance?:0.0,
        fullyDilutedMarketCap = fullyDilutedMarketCap?:0.0,
        updatedAt = updatedAt?:""
    )
}


