package ru.haliksar.flowapp.libraries.network.providers

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val WaitTime = 3000L
private const val ConnTime = 1000L

fun provideOkHttpClient(
    interceptors: List<Interceptor> = emptyList(),
    authenticators: List<Authenticator> = emptyList()
): OkHttpClient =
    OkHttpClient.Builder()
        //default timeout for not annotated requests
        .callTimeout(WaitTime, TimeUnit.SECONDS)
        .readTimeout(WaitTime, TimeUnit.MILLISECONDS)
        .connectTimeout(ConnTime, TimeUnit.MILLISECONDS)
        .writeTimeout(WaitTime, TimeUnit.MILLISECONDS)
        .apply {
            interceptors.forEach { addInterceptor(it) }
            authenticators.forEach { authenticator(it) }
        }
        .build()