package flowapp.features.news.domain.entity

import java.util.*

data class NewsEntity(
    val title: String,
    val description: String,
    val author: AuthorEntity,
    val pictures: List<UrlEntity>?,
    val postDate: Date
)