package ru.haliksar.flowapp.libraries.fakenetwork.methods

import android.content.Context
import android.net.Uri
import okhttp3.Response
import ru.haliksar.flowapp.libraries.fakenetwork.ext.error404

internal fun get(context: Context, uri: Uri, response: Response.Builder): Response.Builder =
    when (uri.path) {
        else -> {
            response.error404()
        }
    }