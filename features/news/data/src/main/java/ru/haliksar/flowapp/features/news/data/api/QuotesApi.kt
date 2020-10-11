package ru.haliksar.flowapp.features.news.data.api

import retrofit2.http.POST
import retrofit2.http.Query
import ru.haliksar.flowapp.features.news.data.dto.QuotesDto

interface QuotesApi {
    companion object {
        const val URL = "/api/v1/quotes/"
    }

    @POST(URL)
    suspend fun getQuotes(
        @Query("page") page: Int,
        @Query("limit") size: Int
    ): List<QuotesDto>
}