package ru.haliksar.flowapp.libraries.network.providers

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.haliksar.flowapp.libraries.network.converters.NullOnEmptyConverterFactory
import ru.haliksar.libraries.retroflow.RetroFlowCallAdapterFactory

fun provideRetrofit(
    gson: Gson,
    okHttpClient: OkHttpClient,
    url: String
): Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RetroFlowCallAdapterFactory.create())
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(NullOnEmptyConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(okHttpClient)
    .baseUrl(url)
    .build()
