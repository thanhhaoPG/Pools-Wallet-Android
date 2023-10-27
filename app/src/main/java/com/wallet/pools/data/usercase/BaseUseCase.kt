package com.wallet.pools.data.usercase

interface BaseUseCase<in Parameter, out Result> {
   suspend fun execute (params: Parameter): Result
}
