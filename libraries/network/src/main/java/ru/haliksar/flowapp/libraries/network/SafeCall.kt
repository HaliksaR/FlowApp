package ru.haliksar.flowapp.libraries.network

import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.NoConnectivityException
import ru.haliksar.libraries.retroflow.wrapper.Resource
import java.net.UnknownHostException

suspend fun <T : Any?> safeCall(
    action: suspend () -> Resource<T?, NetworkException>,
    errorEmpty: Boolean = true
): Resource<T?, NetworkException> =
    when (val response = action()) {
        Resource.Loading -> response

        is Resource.Success -> {
            if (errorEmpty && response.data == null) {
                Resource.Error(
                    NetworkException(
                        message = "Body is Empty!",
                        code = NetworkCode.EMPTY_BODY
                    )
                )
            } else {
                response
            }
        }

        is Resource.Error -> checkThrowable(response, action)
    }

private suspend fun <T : Any?> checkThrowable(
    response: Resource.Error<Throwable>,
    action: suspend () -> Resource<T?, NetworkException>
): Resource.Error<NetworkException> =
    when (val t = response.throwable) {
        is UnknownHostException -> {
            Resource.Error(
                NetworkException(
                    message = t.message,
                    code = NetworkCode.SERVER_CONNECTION_ERROR
                )
            )
        }

        is NoConnectivityException -> {
            Resource.Error(
                NetworkException(
                    message = t.message,
                    code = NetworkCode.INTERNET_CONNECTION_ERROR
                )
            )
        }

        is JsonDataException -> {
            Resource.Error(
                NetworkException(
                    message = t.message,
                    code = NetworkCode.MISSING_VALUE
                )
            )
        }

        is HttpException -> {
            Resource.Error(
                NetworkException(
                    message = t.message,
                    code = t.code()
                )
            )
        }

        else -> {
            Resource.Error(NetworkException(message = t.message, code = NetworkCode.UNKNOWN))
        }
    }