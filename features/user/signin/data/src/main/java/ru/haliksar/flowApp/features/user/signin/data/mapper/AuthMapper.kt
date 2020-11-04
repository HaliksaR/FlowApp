package ru.haliksar.flowApp.features.user.signin.data.mapper

import ru.haliksar.flowApp.features.user.signin.data.dto.AuthDto
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity

fun AuthEntity.toDto() = AuthDto(accessToken)

fun AuthDto.toEntity() = AuthEntity(accessToken)