package ru.haliksar.flowapp.libraries.fakenetwork.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.libraries.fakenetwork.fakeServerInterceptor
import ru.haliksar.flowapp.libraries.network.di.FAKE
import ru.haliksar.flowapp.libraries.network.di.LOG_INTERCEPTOR
import ru.haliksar.flowapp.libraries.network.di.NO_CONNECT_INTERCEPTOR
import ru.haliksar.flowapp.libraries.network.di.TIMEOUT_INTERCEPTOR
import ru.haliksar.flowapp.libraries.network.providers.provideOkHttpClient
import ru.haliksar.flowapp.libraries.network.providers.provideRetrofit

const val FAKE_SERVER_INTERCEPTOR = "FAKE_SERVER_INTERCEPTOR"

val FakeNetworkModule = module {
    factory(named(FAKE_SERVER_INTERCEPTOR)) { fakeServerInterceptor(androidContext()) }

    factory(named(FAKE)) {
        provideOkHttpClient(
            interceptors = listOf(
                get(named(TIMEOUT_INTERCEPTOR)),
                get(named(LOG_INTERCEPTOR)),
                get(named(FAKE_SERVER_INTERCEPTOR)),
                get(named(NO_CONNECT_INTERCEPTOR))
            )
        )
    }
    factory(named(FAKE)) {
        provideRetrofit(
            okHttpClient = get(named(FAKE)),
            url = "https://www.haliksar.fun"
        )
    }
}