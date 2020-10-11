package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.news.data.di.NEWS_MAPPER_DTO
import ru.haliksar.flowapp.features.news.data.dto.NewsMapperDtoT
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperDto
import ru.haliksar.flowapp.libraries.core.data.mapper.toListEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow

@KoinApiExtension
class NewsDataSourceImpl(private val api: NewsApi) : NewsDataSource, KoinComponent {

    private val mapper by mapperDto<NewsMapperDtoT>(named(NEWS_MAPPER_DTO))

    override fun getNews(pageStartIndex: Int): Flow<NetResponse<List<NewsEntity>>> =
        safeCallFlow { api.getNews(pageStartIndex).toListEntity(mapper) }

}