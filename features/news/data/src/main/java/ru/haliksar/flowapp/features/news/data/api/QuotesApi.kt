package ru.haliksar.flowapp.features.news.data.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST
import retrofit2.http.Query
import ru.haliksar.flowapp.features.news.data.dto.QuotesDto

interface QuotesApi {
    companion object {
        const val URL = "/api/v1/quotes/"
    }

    @POST(URL)
    fun getQuotes(@Query("pageNumber") pageNumber: Int): Flow<List<QuotesDto>?>
}