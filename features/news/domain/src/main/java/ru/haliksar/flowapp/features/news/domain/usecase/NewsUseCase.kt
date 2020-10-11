package ru.haliksar.flowapp.features.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

typealias NewsUseCaseT = UseCase<Flow<NetResponse<List<NewsEntity>>>, Int>

class NewsUseCase(private val repository: NewsRepository) : NewsUseCaseT {

    override operator fun invoke(param: Int): Flow<NetResponse<List<NewsEntity>>> =
        repository.getNews(param)
}