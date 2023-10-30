package com.wallet.pools.data.usercase

import com.wallet.pools.data.request.RequestDataChart
import com.wallet.pools.domain.model.ListDataChartDomain
import com.wallet.pools.domain.repository.MyRepository
import com.wallet.pools.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


typealias DataChartBaseUserCase = BaseUseCase<RequestDataChart, Flow<Resource<ListDataChartDomain>>>

class DataChartUserCase @Inject constructor(
    private val myRepository: MyRepository
) : DataChartBaseUserCase {
    override suspend fun execute(params: RequestDataChart) =
        myRepository.getDataChart(params)
}