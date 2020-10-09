package ru.haliksar.flowapp.features.news.domain.entity

import java.util.*

data class NewsEntity(
    val id: Int,
    val title: String,
    val description: String,
    val author: AuthorEntity,
    val pictures: List<UrlEntity>?,
    val postDate: Date
)