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
    emit(NetworkResponse.Loading)
    try {
        action().collect { emit(NetworkResponse.Success(it)) }
    } catch (throwable: Throwable) {
        emit(checkThrowable(throwable))
    }
}

fun <T> safeCallFlowEmpty(
    action: suspend () -> Flow<T?>
): Flow<NetworkResponse<out T?>> = flow {
    emit(NetworkResponse.Loading)
    try {
        action().collect { emit(NetworkResponse.Success(it)) }
    } catch (npe: KotlinNullPointerException) {
        NetworkResponse.Success(
            null
        )
    } catch (httpE: HttpException) {
        emit(checkThrowable(httpE))
    } catch (throwable: Throwable) {
        emit(checkThrowable(throwable))
    }
}

suspend fun <T> safeCall(
    action: suspend () -> T
): NetworkResponse<out T> = try {
    NetworkResponse.Success(action())
} catch (throwable: Throwable) {
    checkThrowable(throwable)
}

fun Throwable.toNetworkException(): NetworkException {
    val code = when (this) {
        is UnknownHostException -> NetworkCode.SERVER_CONNECTION_ERROR
        is NoConnectivityException -> NetworkCode.INTERNET_CONNECTION_ERROR
        is HttpException -> this.code()
        is JsonParseException -> NetworkCode.MISSING_VALUE
        is KotlinNullPointerException -> NetworkCode.EMPTY_BODY
        else -> NetworkCode.UNKNOWN
    }
    return NetworkException(
        message = this.message,
        code = code,
        throwable = this
    )
}

private fun checkThrowable(throwable: Throwable): NetworkResponse.Error =
    NetworkResponse.Error(throwable.toNetworkException())