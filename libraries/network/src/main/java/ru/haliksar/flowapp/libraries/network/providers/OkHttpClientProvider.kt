package ru.haliksar.flowapp.libraries.network.providers

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient

internal fun provideOkHttpClient(
    interceptors: List<Interceptor> = emptyList(),
    authenticators: List<Authenticator> = emptyList()
): OkHttpClient =
    OkHttpClient.Builder()
        .apply {
            interceptors.forEach { addInterceptor(it) }
            authenticators.forEach { authenticator(it) }
        }
        .build()