package ru.haliksar.flowapp.features.news.shared.data.mapper

import ru.haliksar.flowapp.features.news.shared.data.dto.UrlDto
import ru.haliksar.flowapp.features.news.shared.domain.entity.UrlEntity

fun UrlEntity.toDto() = UrlDto(link, text)

fun UrlDto.toEntity() = UrlEntity(link, text)