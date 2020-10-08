package flowapp.features.news.domain.usecase

import flowapp.features.news.domain.entity.NewsEntity
import flowapp.features.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.libraries.core.domain.UseCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

typealias NewsUseCaseT = UseCase<Flow<NetworkResponse<List<NewsEntity>?>>, String>

class NewsUseCase(private val repository: NewsRepository) : NewsUseCaseT {

    override operator fun invoke(param: String): Flow<NetworkResponse<List<NewsEntity>?>> =
        repository.getNews(param)
}