package ru.haliksar.flowapp.libraries.network.wrappers

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error(val exception: NetworkException) : NetworkResponse<Nothing>()
}