package ru.haliksar.flowapp.libraries.network.okhttp.interceptors

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

internal fun loggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)