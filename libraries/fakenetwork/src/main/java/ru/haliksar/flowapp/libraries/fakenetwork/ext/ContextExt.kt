package ru.haliksar.flowapp.libraries.fakenetwork.ext

import android.content.Context
import androidx.annotation.RawRes
import okhttp3.Response
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkCode

internal fun Context.getJson(res: Int): String =
    resources.openRawResource(res).bufferedReader().use { it.readText() }


internal fun <K> Response.Builder.createWithQuery(
    key: K,
    context: Context,
    queryByJson: Map<K, FakeResponse>,
    defaultResponse: FakeResponse? = null
): Response.Builder {
    val response = queryByJson[key]
    return if (response == null) {
        if (defaultResponse == null) {
            error404()
        } else {
            create(
                code = defaultResponse.code,
                description = defaultResponse.description,
                body = defaultResponse.res?.let { context.getJson(it) } ?: ""
            )
        }
    } else {
        create(
            code = response.code,
            description = response.description,
            body = response.res?.let { context.getJson(it) } ?: ""
        )
    }
}

internal data class FakeResponse(
    val code: Int = NetworkCode.OK,
    val description: String,
    @RawRes val res: Int? = null
)