package com.wallet.pools.data.usercase

import com.wallet.pools.data.request.RequestWatchMaket
import com.wallet.pools.domain.model.WatchMaketDomain
import com.wallet.pools.domain.repository.MyRepository
import com.wallet.pools.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias WatchMaketBaseUseCase = BaseUseCase<RequestWatchMaket, Flow<Resource<WatchMaketDomain>>>

class WatchMaketUseCase @Inject constructor(
    private val myRepository: MyRepository
) : WatchMaketBaseUseCase {
    override suspend fun execute(params: RequestWatchMaket) =
        myRepository.getWatchMaket(params)
}