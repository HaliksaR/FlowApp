package ru.haliksar.flowapp.features.news.data.dto

import com.google.gson.annotations.SerializedName
import ru.haliksar.flowapp.features.news.domain.entity.UrlEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto

data class UrlDto(
    @SerializedName("link") val link: String,
    @SerializedName("text") val text: String
)

typealias UrlMapperDtoT = MapperDto<UrlEntity, UrlDto>

class UrlMapperDto : UrlMapperDtoT() {

    override fun toDto(entity: UrlEntity): UrlDto =
        UrlDto(entity.link, entity.text)

    override fun toEntity(dto: UrlDto): UrlEntity =
        UrlEntity(
            dto.link,
            dto.text
        )
}