package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity

interface NewsDataSource {
    fun getNews(pageStartIndex: Int): Flow<List<NewsEntity>>
}