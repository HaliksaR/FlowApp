package ru.haliksar.flowapp.features.news.data.repository

import ru.haliksar.flowapp.features.news.data.datasource.NewsDataSource
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository

class NewsRepositoryImpl(private val dataSource: NewsDataSource) : NewsRepository {

    override suspend fun getNews(pageStartIndex: Int): List<NewsEntity> =
        dataSource.getNews(pageStartIndex)
}