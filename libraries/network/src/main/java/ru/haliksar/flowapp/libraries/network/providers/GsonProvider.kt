package ru.haliksar.flowapp.libraries.network.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ru.haliksar.flowapp.libraries.network.converters.GsonKotlinAdapterFactory
import ru.haliksar.flowapp.libraries.network.converters.Rfc3339DateJsonAdapter
import java.util.*

fun provideGson(): Gson =
    GsonBuilder()
        .registerTypeAdapterFactory(GsonKotlinAdapterFactory.create())
        .registerTypeAdapter(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .create()
