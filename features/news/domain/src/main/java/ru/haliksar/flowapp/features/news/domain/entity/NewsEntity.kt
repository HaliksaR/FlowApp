package ru.haliksar.flowapp.features.news.domain.entity

import ru.haliksar.flowapp.libraries.core.domain.entity.AuthorEntity
import ru.haliksar.flowapp.libraries.core.domain.entity.UrlEntity
import java.util.*


data class NewsEntity(
    val id: Int,
    val title: String,
    val description: String,
    val author: AuthorEntity,
    val pictures: List<UrlEntity>?,
    val postDate: Date
)