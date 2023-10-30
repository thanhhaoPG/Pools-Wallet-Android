package com.wallet.pools.domain.model



data class ListDataChartDomain(
    val quotes: List<DataChart>,
)

data class DataChart(
    val time: String?,
    val value: Double?,
)