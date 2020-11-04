package ru.haliksar.flowapp.features.news.shared.data.mapper

import ru.haliksar.flowapp.features.news.shared.data.dto.AuthorDto
import ru.haliksar.flowapp.features.news.shared.domain.entity.AuthorEntity

fun AuthorEntity.toDto() =
    AuthorDto(
        name = name,
        surname = surname,
        profileUrl = profileUrl.toDto(),
        avatarUrl = avatarUrl,
    )

fun AuthorDto.toEntity() =
    AuthorEntity(
        name = name,
        surname = surname,
        profileUrl = profileUrl.toEntity(),
        avatarUrl = avatarUrl,
    )
