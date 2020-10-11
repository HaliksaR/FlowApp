package ru.haliksar.flowapp.libraries.core.data.dto

import com.google.gson.annotations.SerializedName
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.libraries.core.data.di.URL_MAPPER_DTO
import ru.haliksar.flowapp.libraries.core.data.mapper.MapperDto
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperDto
import ru.haliksar.flowapp.libraries.core.domain.entity.AuthorEntity

data class AuthorDto(
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("profileUrl") val profileUrl: UrlDto,
    @SerializedName("avatarUrl") val avatarUrl: String
)

typealias AuthorMapperDtoT = MapperDto<AuthorEntity, AuthorDto>

@KoinApiExtension
class AuthorMapperDto : AuthorMapperDtoT(), KoinComponent {

    private val urlMapper by mapperDto<UrlMapperDtoT>(named(URL_MAPPER_DTO))

    override fun toDto(entity: AuthorEntity): AuthorDto =
        AuthorDto(
            entity.name,
            entity.surname,
            urlMapper.toDto(entity.profileUrl),
            entity.avatarUrl,
        )

    override fun toEntity(dto: AuthorDto): AuthorEntity =
        AuthorEntity(
            dto.name,
            dto.surname,
            urlMapper.toEntity(dto.profileUrl),
            dto.avatarUrl,
        )
}