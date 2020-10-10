package ru.haliksar.flowapp.features.news.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

interface QuotesRepository {
    fun getQuotes(pageStartIndex: Int): Flow<NetworkResponse<out List<QuotesEntity>>>
}