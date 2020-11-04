package ru.haliksar.flowapp.features.news.domain.usecase

import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {

    suspend operator fun invoke(param: Int): List<NewsEntity> =
        repository.getNews(param)
}