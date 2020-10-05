package ru.haliksar.flowapp.libraries.fakenetwork.methods

import android.content.Context
import android.net.Uri
import okhttp3.Response
import ru.haliksar.flowapp.libraries.fakenetwork.R
import ru.haliksar.flowapp.libraries.fakenetwork.ext.create
import ru.haliksar.flowapp.libraries.fakenetwork.ext.error404
import ru.haliksar.flowapp.libraries.fakenetwork.ext.getJson

internal fun post(context: Context, uri: Uri, response: Response.Builder): Response.Builder =
    when (uri.path) {
        "/api/test" -> {
            response.create(
                code = 200,
                description = "Повторный запрос кода", body = context.getJson(R.raw.test)
            )
        }

        else -> {
            response.error404()
        }
    }