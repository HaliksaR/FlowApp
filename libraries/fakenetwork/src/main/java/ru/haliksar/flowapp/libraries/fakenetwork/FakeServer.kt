package ru.haliksar.flowapp.libraries.fakenetwork

import android.content.Context
import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import ru.haliksar.flowapp.libraries.fakenetwork.ext.error404
import ru.haliksar.flowapp.libraries.fakenetwork.methods.get
import ru.haliksar.flowapp.libraries.fakenetwork.methods.post

private const val GET = "GET"
private const val POST = "POST"

internal const val CONTENT_TYPE = "content-type"
internal const val MEDIA_TYPE_JSON = "application/json"

internal fun fakeServerInterceptor(context: Context) = Interceptor { chain ->
    val response = Response.Builder()
        .request(chain.request())
        .protocol(Protocol.HTTP_2)
        .addHeader(CONTENT_TYPE, MEDIA_TYPE_JSON)
    startFakeResponse(context, chain.request().url.toString(), response, chain).build()
}

private fun startFakeResponse(
    context: Context,
    url: String,
    response: Response.Builder,
    chain: Interceptor.Chain
): Response.Builder {
    val uri = Uri.parse(url)
    return when (chain.request().method) {
        GET -> get(context, uri, response)
        POST -> post(context, uri, response)
        else -> response.error404()
    }
}