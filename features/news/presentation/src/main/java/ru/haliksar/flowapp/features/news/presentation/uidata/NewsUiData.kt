package ru.haliksar.flowapp.features.news.presentation.uidata

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.presentation.di.AUTHOR_MAPPER_UIDATA
import ru.haliksar.flowapp.features.news.presentation.di.URL_MAPPER_UIDATA
import ru.haliksar.flowapp.libraries.core.data.MapperUiData
import ru.haliksar.flowapp.libraries.core.data.mapperUiData
import java.util.*

data class NewsUiData(
    val title: String,
    val description: String,
    val author: AuthorUiData,
    val pictures: List<UrlUiData>?,
    val postDate: Date
)

typealias NewsMapperUiDataT = MapperUiData<NewsEntity, NewsUiData>

@KoinApiExtension
class NewsMapperUiData : NewsMapperUiDataT(), KoinComponent {

    private val authorMapper by mapperUiData<AuthorMapperUiDataT>(named(AUTHOR_MAPPER_UIDATA))
    private val urlMapper by mapperUiData<UrlMapperUiDataT>(named(URL_MAPPER_UIDATA))

    override fun toUiData(entity: NewsEntity): NewsUiData =
        NewsUiData(
            entity.title,
            entity.description,
            authorMapper.toUiData(entity.author),
            entity.pictures?.map { urlMapper.toUiData(it) },
            entity.postDate,
        )

    override fun toEntity(uiData: NewsUiData): NewsEntity =
        NewsEntity(
            uiData.title,
            uiData.description,
            authorMapper.toEntity(uiData.author),
            uiData.pictures?.map { urlMapper.toEntity(it) },
            uiData.postDate,
        )
}