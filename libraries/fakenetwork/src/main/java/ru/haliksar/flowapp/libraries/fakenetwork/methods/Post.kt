package ru.haliksar.flowapp.libraries.fakenetwork.methods

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.Response
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.libraries.fakenetwork.R
import ru.haliksar.flowapp.libraries.fakenetwork.ext.*

internal fun post(context: Context, uri: Uri, response: Response.Builder): Response.Builder =
    when (uri.path) {
        SignInApi.URL -> {
            response.create(
                code = 200,
                description = "Логин", body = context.getJson(R.raw.sign_in)
            )
        }
        NewsApi.URL -> {
            Log.d("createWithQuery", uri.query.toString())
            response.createWithQuery(
                context = context,
                key = uri.query,
                defaultResponse = FakeResponse(description = "", res = R.raw.news_part_empty),
                queryByJson = mapOf<String?, FakeResponse>(
                    "pageNumber=0" to FakeResponse(
                        description = "",
                        res = R.raw.news_part0
                    ),
                    "pageNumber=1" to FakeResponse(
                        description = "",
                        res = R.raw.news_part1
                    ),
                    "pageNumber=2" to FakeResponse(
                        code = 404,
                        description = "",
                        res = null
                    )
                )
            )
        }
        else -> {
            response.error404()
        }
    }