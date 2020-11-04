package ru.haliksar.flowApp.features.user.signin.data.mapper

import ru.haliksar.flowApp.features.user.signin.data.dto.SignInDto
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity

fun SignInEntity.toDto() = SignInDto(login, password)

fun SignInDto.toEntity() = SignInEntity(login, password)