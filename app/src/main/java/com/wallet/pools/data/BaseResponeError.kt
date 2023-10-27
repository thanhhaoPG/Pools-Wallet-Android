package com.wallet.pools.data

import com.wallet.pools.util.Resource
import kotlinx.coroutines.flow.FlowCollector
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

suspend inline fun <reified T> FlowCollector<Resource<T>>.handleError(throwable: Throwable) {
    Timber.i("handleError: ${throwable.message}")
    this.apply {
        try {
            var errorMessage = ""
            var statusCode = 500
            val response = (throwable as? HttpException)?.response()

            response?.errorBody()?.let { responseBody ->
                try {
                    val jObjError = JSONObject(responseBody.string())
                    statusCode = jObjError.getString("statusCode").toInt()
                    errorMessage = jObjError.getString("message")

                    emit(Resource.Error(message = errorMessage, statusCode = statusCode))
                    return

                } catch (ex: Throwable) {
                    emit(Resource.Error(message = errorMessage, statusCode = statusCode))
                    return
                }
            }
            emit(
                Resource.Error(
                    message = "No address associated with hostname",
                    statusCode = statusCode
                )
            )


        } catch (ex: Exception) {
            emit(
                Resource.Error(
                    message = "No address associated with hostname",
                    statusCode = 500
                )
            )

        }
    }
}



