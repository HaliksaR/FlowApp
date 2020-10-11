package ru.haliksar.flowapp.libraries.network.pagingwrappers

import androidx.paging.PagingSource
import ru.haliksar.flowapp.libraries.network.wrappers.toNetworkException

suspend fun <V : Any> safeCallPaging(
    page: Int,
    defaultPage: Int,
    action: suspend () -> List<V>
): PagingSource.LoadResult<Int, V> = try {
    val response = action()
    val prevKey = if (page == defaultPage) {
        null
    } else {
        page - 1
    }
    val nextKey = if (response.isEmpty()) {
        null
    } else {
        page + 1
    }
    PagingSource.LoadResult.Page(response, prevKey = prevKey, nextKey = nextKey)
} catch (exception: Exception) {
    PagingSource.LoadResult.Error(exception.toNetworkException())
}