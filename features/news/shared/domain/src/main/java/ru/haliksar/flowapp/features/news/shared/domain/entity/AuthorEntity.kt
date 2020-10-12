package ru.haliksar.flowapp.features.news.shared.domain.entity

data class AuthorEntity(
    val name: String,
    val surname: String,
    val profileUrl: UrlEntity,
    val avatarUrl: String
)
