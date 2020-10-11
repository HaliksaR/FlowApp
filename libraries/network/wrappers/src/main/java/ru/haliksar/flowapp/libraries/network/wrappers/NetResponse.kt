package ru.haliksar.flowapp.libraries.network.wrappers

sealed class NetResponse<out T> {
    data class Success<out T>(val data: T) : NetResponse<T>()
    data class Error(val exception: NetworkException) : NetResponse<Nothing>()
}
