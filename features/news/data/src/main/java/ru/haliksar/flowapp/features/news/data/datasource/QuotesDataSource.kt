package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity

interface QuotesDataSource {
    fun getQuotes(pageStartIndex: Int): Flow<List<QuotesEntity>>
}