package com.wallet.pools.data.usercase

import com.wallet.pools.data.request.RequestWatchMaket
import com.wallet.pools.domain.model.WatchMarketDomain
import com.wallet.pools.domain.repository.MyRepository
import com.wallet.pools.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias WatchMarketBaseUseCase = BaseUseCase<RequestWatchMaket, Flow<Resource<WatchMarketDomain>>>

class WatchMarketUseCase @Inject constructor(
    private val myRepository: MyRepository
) : WatchMarketBaseUseCase {
    override suspend fun execute(params: RequestWatchMaket) =
        myRepository.getWatchMaket(params)
}