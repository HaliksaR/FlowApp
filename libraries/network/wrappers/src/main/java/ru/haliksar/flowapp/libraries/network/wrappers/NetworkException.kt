package ru.haliksar.flowapp.libraries.network.wrappers

open class NetworkException(
    override val message: String? = null,
    val code: Int? = null,
    val exception: Exception
) : Exception()