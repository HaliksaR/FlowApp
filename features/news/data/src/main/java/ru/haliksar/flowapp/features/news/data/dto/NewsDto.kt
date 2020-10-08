package ru.haliksar.flowapp.features.news.data.dto

import com.google.gson.annotations.SerializedName
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto
import ru.haliksar.flowapp.libraries.core.data.mapperDto
import java.util.*

data class NewsDto(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("author") val author: AuthorDto,
    @SerializedName("pictures") val pictures: List<UrlDto>?,
    @SerializedName("postDate") val postDate: Date
)

typealias NewsMapperDtoT = MapperDto<NewsEntity, NewsDto>

@KoinApiExtension
class NewsMapperDto : NewsMapperDtoT(), KoinComponent {

    private val authorMapper by mapperDto<AuthorMapperDtoT>()
    private val urlMapper by mapperDto<UrlMapperDtoT>()

    override fun toDto(entity: NewsEntity): NewsDto =
        NewsDto(
            entity.title,
            entity.description,
            authorMapper.toDto(entity.author),
            entity.pictures?.map { urlMapper.toDto(it) },
            entity.postDate,
        )

    override fun toEntity(dto: NewsDto): NewsEntity =
        NewsEntity(
            dto.title,
            dto.description,
            authorMapper.toEntity(dto.author),
            dto.pictures?.map { urlMapper.toEntity(it) },
            dto.postDate,
        )
}