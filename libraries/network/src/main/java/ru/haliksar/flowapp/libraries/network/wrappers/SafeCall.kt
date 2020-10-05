package ru.haliksar.flowapp.libraries.network.wrappers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.haliksar.flowapp.libraries.network.NetworkCode
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.NoConnectivityException
import java.net.UnknownHostException

suspend fun <T> safeCallFlow(
    action: suspend () -> Flow<T>
): Flow<NetworkResponse<out T>> = flow {
    emit(NetworkResponse.Loading)
    try {
        action().collect { emit(NetworkResponse.Success(it)) }
    } catch (throwable: Throwable) {
        emit(
            checkThrowable(
                throwable
            )
        )
    }
}

suspend fun <T> safeCallFlowEmpty(
    action: suspend () -> Flow<T?>
): Flow<NetworkResponse<out T?>> = flow {
    emit(NetworkResponse.Loading)
    try {
        action().collect { emit(NetworkResponse.Success(it)) }
    } catch (npe: KotlinNullPointerException) {
        NetworkResponse.Success(null)
    } catch (httpE: HttpException) {
        emit(checkThrowable(httpE))
    } catch (throwable: Throwable) {
        emit(
            checkThrowable(
                throwable
            )
        )
    }
}

suspend fun <T> safeCall(
    action: suspend () -> T
): NetworkResponse<out T> {
    return try {
        NetworkResponse.Success(action())
    } catch (throwable: Throwable) {
        checkThrowable(throwable)
    }
}

fun Throwable.toNetworkException(): NetworkException {
    val code = when (this) {
        is UnknownHostException -> NetworkCode.SERVER_CONNECTION_ERROR
        is NoConnectivityException -> NetworkCode.INTERNET_CONNECTION_ERROR
        is HttpException -> this.code()
        is KotlinNullPointerException -> NetworkCode.EMPTY_BODY
        else -> NetworkCode.UNKNOWN
    }
    return NetworkException(
        message = this.message,
        code = code,
        throwable = this
    )
}

private fun checkThrowable(
    throwable: Throwable
): NetworkResponse.Error {
    val code = when (throwable) {
        is UnknownHostException -> NetworkCode.SERVER_CONNECTION_ERROR
        is NoConnectivityException -> NetworkCode.INTERNET_CONNECTION_ERROR
        is HttpException -> throwable.code()
        is KotlinNullPointerException -> NetworkCode.EMPTY_BODY
        else -> NetworkCode.UNKNOWN
    }
    return NetworkResponse.Error(
        NetworkException(
            message = throwable.message,
            code = code,
            throwable = throwable
        )
    )
}