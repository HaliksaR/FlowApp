package ru.haliksar.flowapp.features.news.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

interface NewsRepository {
    fun getNews(authKey: String): Flow<NetworkResponse<out List<NewsEntity>>>
}