package com.wallet.pools.data.repository

import com.wallet.pools.data.remote.MyApi
import com.wallet.pools.domain.repository.MyRepository
import javax.inject.Inject

class MyImpl @Inject constructor(
    val api: MyApi
) : MyRepository {

//    override suspend fun loginUser(request: RequestLogin): Flow<Resource<LoginDomain>> {
//        return flow {
//            emit(Resource.Loading())
//            val user = api.loginEmail(request).data?.toDomain()
//            user?.let { emit(Resource.Success(it)) }
//        }.catch {
//            this.handleError(it)
//        }
//    }

}