package ru.haliksar.flowApp.features.user.accounts.domain.entity

data class UserEntity(
    val id: Int,
    val name: String,
    val surname: String,
    val age: Int,
    val login: String
)