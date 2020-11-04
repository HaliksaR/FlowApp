package ru.haliksar.flowApp.features.user.signin.presentation.mapper

import kotlinx.coroutines.flow.MutableStateFlow
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInUiData

fun SignInEntity.toUiData() = SignInUiData(
    login = MutableStateFlow(login),
    password = MutableStateFlow(password)
)

fun SignInUiData.toEntity() = SignInEntity(
    login = login.value,
    password = password.value
)