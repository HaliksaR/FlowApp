package ru.haliksar.flowapp.features.quotes.domain.entity

import ru.haliksar.flowapp.libraries.core.domain.entity.AuthorEntity

data class QuotesEntity(
    val author: AuthorEntity,
    val quote: String
)
