package ru.haliksar.flowApp.features.user.accounts.data.mapper

import ru.haliksar.flowApp.features.user.accounts.data.dto.UserDto
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity

fun UserEntity.toDto() = UserDto(id, name, surname, age, login)

fun UserDto.toEntity() = UserEntity(id, name, surname, age, login)

fun List<UserDto>.toListEntity() = map { it.toEntity() }

fun List<UserEntity>.toListDto() = map { it.toDto() }