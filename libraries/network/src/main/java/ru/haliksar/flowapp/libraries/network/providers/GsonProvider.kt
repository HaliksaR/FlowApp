package ru.haliksar.flowapp.libraries.network.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ru.haliksar.flowapp.libraries.network.converters.GsonKotlinAdapterFactory

fun provideGson(): Gson =
    GsonBuilder()
        .registerTypeAdapterFactory(GsonKotlinAdapterFactory.create())
        .create()