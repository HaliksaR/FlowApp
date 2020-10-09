package ru.haliksar.flowapp.features.news.data.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST
import retrofit2.http.Query
import ru.haliksar.flowapp.features.news.data.dto.NewsDto

interface NewsApi {
    companion object {
        const val URL = "/api/v1/news/"
    }

    @POST(URL)
    fun getNews(@Query("pageNumber") pageNumber: Int): Flow<List<NewsDto>?>
}