package ru.haliksar.flowapp.libraries.fakenetwork.di

import okhttp3.Interceptor
import okhttp3.Protocol
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.fakeokhttpinterceptor.FakeOkHttpInterceptor
import ru.haliksar.flowapp.libraries.fakenetwork.BuildConfig
import ru.haliksar.flowapp.libraries.fakenetwork.handler.PostMethodHandler
import ru.haliksar.flowapp.libraries.network.di.FAKE
import ru.haliksar.flowapp.libraries.network.di.LOG_INTERCEPTOR
import ru.haliksar.flowapp.libraries.network.di.NO_CONNECT_INTERCEPTOR
import ru.haliksar.flowapp.libraries.network.di.TIMEOUT_INTERCEPTOR
import ru.haliksar.flowapp.libraries.network.providers.provideOkHttpClient
import ru.haliksar.flowapp.libraries.network.providers.provideRetrofit

const val FAKE_SERVER_INTERCEPTOR = "FAKE_SERVER_INTERCEPTOR"

val FakeNetworkModule = module {
    single<Interceptor>(named(FAKE_SERVER_INTERCEPTOR)) {
        FakeOkHttpInterceptor(
            protocol = Protocol.HTTP_2,
            handlers = listOf(PostMethodHandler(androidContext()))
        )
    }

    single(named(FAKE)) {
        if (BuildConfig.DEBUG) {
            provideOkHttpClient(
                interceptors = listOf(
                    get(named(TIMEOUT_INTERCEPTOR)),
                    get(named(LOG_INTERCEPTOR)),
                    get(named(FAKE_SERVER_INTERCEPTOR)),
                    get(named(NO_CONNECT_INTERCEPTOR))
                )
            )
        } else {
            provideOkHttpClient(
                interceptors = listOf(
                    get(named(TIMEOUT_INTERCEPTOR)),
                    get(named(FAKE_SERVER_INTERCEPTOR)),
                    get(named(NO_CONNECT_INTERCEPTOR))
                )
            )
        }
    }
    single(named(FAKE)) {
        provideRetrofit(
            gson = get(),
            okHttpClient = get(named(FAKE)),
            url = "https://www.haliksar.fun"
        )
    }
}