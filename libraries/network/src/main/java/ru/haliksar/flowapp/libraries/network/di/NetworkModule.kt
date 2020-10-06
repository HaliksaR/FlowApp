package ru.haliksar.flowapp.libraries.network.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.loggingInterceptor
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.noConnectionInterceptor
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.timeoutInterceptor
import ru.haliksar.flowapp.libraries.network.providers.provideOkHttpClient
import ru.haliksar.flowapp.libraries.network.providers.provideRetrofit

const val LOG_INTERCEPTOR = "LOG_INTERCEPTOR"
const val NO_CONNECT_INTERCEPTOR = "NO_CONNECT_INTERCEPTOR"
const val TIMEOUT_INTERCEPTOR = "TIMEOUT_INTERCEPTOR"
const val ORIGINAL = "ORIGINAL"
const val FAKE = "FAKE"

val NetworkModule = module {
    factory(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
    factory(named(TIMEOUT_INTERCEPTOR)) { timeoutInterceptor() }
    factory(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }

    factory(named(ORIGINAL)) {
        provideOkHttpClient(
            interceptors = listOf(
                get(named(TIMEOUT_INTERCEPTOR)),
                get(named(LOG_INTERCEPTOR)),
                get(named(NO_CONNECT_INTERCEPTOR))
            )
        )
    }
    factory(named(ORIGINAL)) {
        provideRetrofit(
            okHttpClient = get(named(ORIGINAL)),
            url = "https://www.haliksar.fun"
        )
    }
}