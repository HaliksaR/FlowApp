package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.news.data.dto.NewsMapperDtoT
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.libraries.core.data.mapperDto
import ru.haliksar.flowapp.libraries.core.data.toListEntityOrEmpty

@KoinApiExtension
class NewsDataSourceImpl(
    private val api: NewsApi
) : NewsDataSource, KoinComponent {

    private val mapper by mapperDto<NewsMapperDtoT>()

    override fun getNews(authKey: String): Flow<List<NewsEntity>> =
        api.getNews(authKey).toListEntityOrEmpty(mapper)
}