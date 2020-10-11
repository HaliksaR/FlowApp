package ru.haliksar.flowapp.libraries.core.domain.entity

data class AuthorEntity(
    val name: String,
    val surname: String,
    val profileUrl: UrlEntity,
    val avatarUrl: String
)
