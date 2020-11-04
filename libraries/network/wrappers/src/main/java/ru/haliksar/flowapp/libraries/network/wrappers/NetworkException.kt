package ru.haliksar.flowapp.libraries.network.wrappers

import com.google.gson.JsonParseException
import retrofit2.HttpException
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException.Code.EMPTY_BODY
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException.Code.INTERNET_CONNECTION_ERROR
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException.Code.MISSING_VALUE
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException.Code.SERVER_CONNECTION_ERROR
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException.Code.UNKNOWN
import java.net.UnknownHostException

class NetworkException(val code: Int, override val message: String? = "") : Exception(message) {
    companion object Code {
        const val INTERNET_CONNECTION_ERROR = 3432523 // допустим
        const val SERVER_CONNECTION_ERROR = 544656 // допустим
        const val MISSING_VALUE = 677755 // допустим
        const val EMPTY_BODY = 65656 // допустим
        const val UNKNOWN = 78885 // допустим
        const val UNAUTHORIZED = 401
        const val NOT_FOUND = 404
        const val OK = 200
        const val FAIL = 400
    }
}

fun Throwable.toNetworkException(): NetworkException {
    val code = when (this) {
        is UnknownHostException -> SERVER_CONNECTION_ERROR
        is NoConnectException -> INTERNET_CONNECTION_ERROR
        is HttpException -> code()
        is JsonParseException -> MISSING_VALUE
        is NullPointerException,
        is KotlinNullPointerException -> EMPTY_BODY
        else -> UNKNOWN
    }
    return NetworkException(
        code = code,
        message = message
    )
}