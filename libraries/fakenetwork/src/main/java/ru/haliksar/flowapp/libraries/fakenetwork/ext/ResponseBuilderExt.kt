package ru.haliksar.flowapp.libraries.fakenetwork.ext

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import ru.haliksar.flowapp.libraries.fakenetwork.MEDIA_TYPE_JSON
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkCode


internal fun Response.Builder.create(
    code: Int = NetworkCode.OK,
    description: String,
    body: String? = null
) =
    code(code)
        .message(description)
        .apply {
            body?.let {
                body(it.toResponseBody(MEDIA_TYPE_JSON.toMediaTypeOrNull()))
            }
        }

internal fun Response.Builder.create(
    code: Int = NetworkCode.OK,
    description: String,
    body: ResponseBody
) =
    code(code)
        .message(description)
        .body(body)

internal fun Response.Builder.error404() =
    create(
        code = NetworkCode.NOT_FOUND,
        description = NetworkCode.NOT_FOUND.toString(),
        body = """{ "error": "NOT API" }"""
    )