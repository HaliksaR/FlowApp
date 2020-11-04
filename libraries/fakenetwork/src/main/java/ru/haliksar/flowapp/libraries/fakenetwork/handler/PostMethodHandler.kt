package ru.haliksar.flowapp.libraries.fakenetwork.handler

import android.content.Context
import android.util.Log
import ru.haliksar.fakeokhttpinterceptor.dsl.defaultResponse
import ru.haliksar.fakeokhttpinterceptor.dsl.request
import ru.haliksar.fakeokhttpinterceptor.dsl.response
import ru.haliksar.fakeokhttpinterceptor.dsl.responseWithQuery
import ru.haliksar.fakeokhttpinterceptor.handlers.Method
import ru.haliksar.fakeokhttpinterceptor.handlers.MethodHandler
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.quotes.data.api.QuotesApi
import ru.haliksar.flowapp.libraries.fakenetwork.R
import ru.haliksar.flowapp.libraries.fakenetwork.ext.getJson

class PostMethodHandler(private val context: Context) : MethodHandler(Method.POST) {
    init {
        request(SignInApi.URL) {
            response {
                code = 200
                message = "Логин"
                body = context.getJson(R.raw.sign_in)
            }
        }
        request(NewsApi.URL) { uri ->
            responseWithQuery(key = uri.query) {
                response("pageNumber=1") {
                    code = 200
                    message = "page 1"
                    body = context.getJson(R.raw.news_part0)
                }
                response("pageNumber=2") {
                    code = 200
                    message = "page 2"
                    body = context.getJson(R.raw.news_part1)
                }
                response("pageNumber=3") { // такой нет
                    code = 200
                    message = "page 3"
                    body = null
                }
                defaultResponse {
                    code = 200
                    message = "page default"
                    body = context.getJson(R.raw.part_empty)
                }
            }
        }
        request(QuotesApi.URL) { uri ->
            Log.d("QuotesApi", uri.toString())
            Log.d("QuotesApi", uri.query.toString())
            responseWithQuery(key = uri.query) {
                response("page=1&limit=60") {
                    code = 200
                    message = "page 1"
                    body = context.getJson(R.raw.quotes_part0)
                }
                response("page=2&limit=20") {
                    code = 200
                    message = "page 2"
                    body = context.getJson(R.raw.quotes_part1)
                }
                response("page=3&limit=20") { // такой нет
                    code = 404
                    message = "page 3"
                    body = null
                }
                defaultResponse {
                    code = 200
                    message = "page default"
                    body = context.getJson(R.raw.part_empty)
                }
            }
        }
    }
}