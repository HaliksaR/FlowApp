package ru.haliksar.flowapp.features.quotes.data.mapper

import ru.haliksar.flowapp.features.news.shared.data.mapper.toDto
import ru.haliksar.flowapp.features.news.shared.data.mapper.toEntity
import ru.haliksar.flowapp.features.quotes.data.dto.QuotesDto
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity

fun QuotesEntity.toDto() = QuotesDto(author.toDto(), quote)

fun QuotesDto.toEntity() = QuotesEntity(author.toEntity(), quote)