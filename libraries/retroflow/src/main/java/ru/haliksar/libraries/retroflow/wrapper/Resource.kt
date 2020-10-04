package ru.haliksar.libraries.retroflow.wrapper

sealed class Resource<out T : Any?, out E : Throwable> {

    object Loading : Resource<Nothing, Nothing>()
    data class Success<out T : Any?>(val data: T) : Resource<T, Nothing>()
    data class Error<out E : Throwable>(val throwable: E) : Resource<Nothing, E>()
}