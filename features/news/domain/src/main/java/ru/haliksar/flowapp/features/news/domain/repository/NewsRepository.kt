package ru.haliksar.flowapp.features.news.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

interface NewsRepository {
    fun getNews(pageStartIndex: Int): Flow<NetResponse<List<NewsEntity>>>
}