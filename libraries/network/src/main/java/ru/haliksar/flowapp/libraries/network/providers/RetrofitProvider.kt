package ru.haliksar.flowapp.libraries.network.providers

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.haliksar.libraries.retroflow.RetroFlowCallAdapterFactory
import ru.haliksar.libraries.retroflow.converters.NullOnEmptyConverterFactory

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    url: String
): Retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(NullOnEmptyConverterFactory.create())
    .addCallAdapterFactory(RetroFlowCallAdapterFactory.create())
    .client(okHttpClient)
    .baseUrl(url)
    .build()