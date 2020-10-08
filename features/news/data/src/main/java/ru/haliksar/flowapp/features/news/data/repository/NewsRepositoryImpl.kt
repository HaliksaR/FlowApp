package ru.haliksar.flowapp.features.news.data.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.data.datasource.NewsDataSource
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow

class NewsRepositoryImpl(private val dataSource: NewsDataSource) :
    NewsRepository {

    override fun getNews(authKey: String): Flow<NetworkResponse<out List<NewsEntity>>> =
        safeCallFlow { dataSource.getNews(authKey) }
}