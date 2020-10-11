package ru.haliksar.flowapp.features.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

typealias NewsUseCaseT = UseCase<Flow<NetworkResponse<out List<NewsEntity>>>, Int>

class NewsUseCase(private val repository: NewsRepository) : NewsUseCaseT {

    override operator fun invoke(param: Int): Flow<NetworkResponse<out List<NewsEntity>>> =
        repository.getNews(param)
}