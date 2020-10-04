package ru.haliksar.flowapp.libraries.network

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.loggingInterceptor
import ru.haliksar.flowapp.libraries.network.okhttp.interceptors.noConnectionInterceptor
import ru.haliksar.flowapp.libraries.network.providers.provideMoshi
import ru.haliksar.flowapp.libraries.network.providers.provideOkHttpClient
import ru.haliksar.flowapp.libraries.network.providers.provideRetrofit

const val LOG_INTERCEPTOR = "loggingInterceptor"
const val NO_CONNECT_INTERCEPTOR = "noConnectionInterceptor"

val NetworkModule = module {
    single(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
    single(named(NO_CONNECT_INTERCEPTOR)) { noConnectionInterceptor(androidContext()) }

    single {
        provideOkHttpClient(
            interceptors = listOf(
                get(named(LOG_INTERCEPTOR)),
                get(named(NO_CONNECT_INTERCEPTOR))
            )
        )
    }
    single { provideMoshi() }
    single {
        provideRetrofit(
            okHttpClient = get(),
            moshi = get(),
            url = "https://www.haliksar.fun"
        )
    }
}