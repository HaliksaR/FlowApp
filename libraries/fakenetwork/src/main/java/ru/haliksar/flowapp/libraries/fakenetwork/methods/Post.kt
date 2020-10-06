package ru.haliksar.flowapp.libraries.fakenetwork.methods

import android.content.Context
import android.net.Uri
import okhttp3.Response
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowapp.libraries.fakenetwork.R
import ru.haliksar.flowapp.libraries.fakenetwork.ext.create
import ru.haliksar.flowapp.libraries.fakenetwork.ext.error404
import ru.haliksar.flowapp.libraries.fakenetwork.ext.getJson

internal fun post(context: Context, uri: Uri, response: Response.Builder): Response.Builder =
    when (uri.path) {
        SignInApi.URL -> {
            response.create(
                code = 200,
                description = "Логин", body = context.getJson(R.raw.sign_in)
            )
        }

        else -> {
            response.error404()
        }
    }