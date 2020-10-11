package ru.haliksar.flowapp.libraries.fakenetwork.methods

import android.content.Context
import android.net.Uri
import okhttp3.Response
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.news.data.api.QuotesApi
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
            response.createWithQuery(
                context = context,
                key = uri.query,
                defaultResponse = FakeResponse(description = "", res = R.raw.part_empty),
                queryByJson = mapOf<String?, FakeResponse>(
                    "pageNumber=1" to FakeResponse(
                        description = "",
                        res = R.raw.news_part0
                    ),
                    "pageNumber=2" to FakeResponse(
                        description = "",
                        res = R.raw.news_part1
                    ),
                    "pageNumber=3" to FakeResponse(
                        code = 404,
                        description = "",
                        res = null
                    )
                )
            )
        }
        QuotesApi.URL -> {
            response.createWithQuery(
                context = context,
                key = uri.query,
                defaultResponse = FakeResponse(description = "", res = R.raw.part_empty),
                queryByJson = mapOf<String?, FakeResponse>(
                    "page=1&limit=60" to FakeResponse(
                        description = "",
                        res = R.raw.quotes_part0
                    ),
                    "page=2&limit=20" to FakeResponse(
                        description = "",
                        res = R.raw.quotes_part1
                    ),
                    "page=3&limit=20" to FakeResponse(
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