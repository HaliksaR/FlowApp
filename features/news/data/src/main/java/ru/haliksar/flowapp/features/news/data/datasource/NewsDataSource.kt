package ru.haliksar.flowapp.features.news.data.datasource

import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity

interface NewsDataSource {

    suspend fun getNews(pageStartIndex: Int): List<NewsEntity>
}