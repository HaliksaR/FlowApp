package ru.haliksar.flowapp.libraries.fakenetwork.ext

import android.content.Context

internal fun Context.getJson(res: Int): String =
    resources.openRawResource(res).bufferedReader().use { it.readText() }