package ru.haliksar.flowapp.features.news.data.mapper

import ru.haliksar.flowapp.features.news.data.dto.NewsDto
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.shared.data.mapper.toDto
import ru.haliksar.flowapp.features.news.shared.data.mapper.toEntity

fun NewsEntity.toDto() =
    NewsDto(
        id,
        title,
        description,
        author.toDto(),
        pictures?.map { it.toDto() },
        postDate,
    )

fun NewsDto.toEntity() =
    NewsEntity(
        id,
        title,
        description,
        author.toEntity(),
        pictures?.map { it.toEntity() },
        postDate,
    )