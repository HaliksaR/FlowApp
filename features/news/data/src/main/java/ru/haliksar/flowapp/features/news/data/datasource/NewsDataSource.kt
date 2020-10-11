package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

interface NewsDataSource {
    fun getNews(pageStartIndex: Int): Flow<NetResponse<List<NewsEntity>>>
}