package ru.haliksar.flowapp.features.news.domain.repository

import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity

interface NewsRepository {
    suspend fun getNews(pageStartIndex: Int): List<NewsEntity>
}