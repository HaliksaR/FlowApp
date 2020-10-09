package ru.haliksar.flowapp.libraries.network.wrappers

import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException

fun <T> safeCallFlow(
    action: suspend () -> Flow<T>
): Flow<NetworkResponse<out T>> = flow {
    try {
        action().collect { emit(NetworkResponse.Success(it)) }
    } catch (exception: Exception) {
        emit(checkException(exception))
    }
}

fun <T> safeCallFlowEmpty(
    action: suspend () -> Flow<T?>
): Flow<NetworkResponse<out T?>> = flow {
    try {
        action().collect { emit(NetworkResponse.Success(it)) }
    } catch (npe: KotlinNullPointerException) {
        NetworkResponse.Success(null)
    } catch (npe: NullPointerException) {
        NetworkResponse.Success(null)
    } catch (httpE: HttpException) {
        emit(checkException(httpE))
    } catch (exception: Exception) {
        emit(checkException(exception))
    }
}

suspend fun <T> safeCall(
    action: suspend () -> T
): NetworkResponse<out T> = try {
    NetworkResponse.Success(action())
} catch (exception: Exception) {
    checkException(exception)
}

fun Exception.toNetworkException(): NetworkException {
    val code = when (this) {
        is UnknownHostException -> NetworkCode.SERVER_CONNECTION_ERROR
        is NoConnectivityException -> NetworkCode.INTERNET_CONNECTION_ERROR
        is HttpException -> this.code()
        is JsonParseException -> NetworkCode.MISSING_VALUE
        is NullPointerException,
        is KotlinNullPointerException -> NetworkCode.EMPTY_BODY
        else -> NetworkCode.UNKNOWN
    }
    return NetworkException(
        message = this.message,
        code = code,
        exception = this
    )
}

private fun checkException(exception: Exception): NetworkResponse.Error =
    NetworkResponse.Error(exception.toNetworkException())