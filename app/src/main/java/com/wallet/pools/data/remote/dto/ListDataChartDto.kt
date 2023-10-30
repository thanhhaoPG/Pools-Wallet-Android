package com.wallet.pools.data.remote.dto

import com.squareup.moshi.Json
import com.wallet.pools.domain.model.DataChart
import com.wallet.pools.domain.model.ListDataChartDomain

class ListDataChartDto (
    @Json(name = "quotes")
    val quotes: List<DataChartDto>
)



data class DataChartDto(
    @Json(name = "time")
    val time: String?,
    @Json(name = "value")
    val value: Double?,
)

fun ListDataChartDto.toDomain() : ListDataChartDomain{
    return ListDataChartDomain(
        quotes = quotes.map { it.toDomain() }
    )
}

fun DataChartDto.toDomain() : DataChart {
    return DataChart(
        value =value?:0.0,
        time = time?:""
    )
}