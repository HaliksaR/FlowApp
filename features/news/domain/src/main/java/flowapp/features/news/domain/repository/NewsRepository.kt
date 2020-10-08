package flowapp.features.news.domain.repository

import flowapp.features.news.domain.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

interface NewsRepository {
    fun getNews(authKey: String): Flow<NetworkResponse<List<NewsEntity>?>>
}