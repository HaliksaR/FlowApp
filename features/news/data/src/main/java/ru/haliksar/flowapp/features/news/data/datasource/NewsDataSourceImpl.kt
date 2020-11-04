package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.news.data.mapper.toEntity
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity

class NewsDataSourceImpl(private val api: NewsApi) : NewsDataSource {

    override suspend fun getNews(pageStartIndex: Int): List<NewsEntity> =
        withContext(Dispatchers.IO) {
            api.getNews(pageNumber = pageStartIndex).map { it.toEntity() }
        }
}