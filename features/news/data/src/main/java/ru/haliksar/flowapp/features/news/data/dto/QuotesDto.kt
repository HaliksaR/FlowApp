package ru.haliksar.flowapp.features.news.data.dto

import com.google.gson.annotations.SerializedName
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.data.di.AUTHOR_MAPPER_DTO
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto
import ru.haliksar.flowapp.libraries.core.data.mapperDto

data class QuotesDto(
    @SerializedName("author") val author: AuthorDto,
    @SerializedName("quote") val quote: String
)

typealias QuotesMapperDtoT = MapperDto<QuotesEntity, QuotesDto>

@KoinApiExtension
class QuotesMapperDto : QuotesMapperDtoT(), KoinComponent {

    private val authorMapper by mapperDto<AuthorMapperDtoT>(named(AUTHOR_MAPPER_DTO))

    override fun toDto(entity: QuotesEntity): QuotesDto =
        QuotesDto(
            authorMapper.toDto(entity.author),
            entity.quote,
        )

    override fun toEntity(dto: QuotesDto): QuotesEntity =
        QuotesEntity(
            authorMapper.toEntity(dto.author),
            dto.quote
        )
}