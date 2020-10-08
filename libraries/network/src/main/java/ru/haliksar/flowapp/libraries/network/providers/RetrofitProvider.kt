package ru.haliksar.flowapp.libraries.network.providers

import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.haliksar.libraries.retroflow.RetroFlowCallAdapterFactory
import ru.haliksar.libraries.retroflow.converters.NullOnEmptyConverterFactory

fun provideRetrofit(
    gson: Gson,
    okHttpClient: OkHttpClient,
    url: String
): Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RetroFlowCallAdapterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(NullOnEmptyConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(okHttpClient)
    .baseUrl(url)
    .build()
