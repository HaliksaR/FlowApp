package ru.haliksar.flowApp.features.user.signin.data.datasource

import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity

interface SignInDataSource {

    suspend fun signIn(data: SignInEntity): AuthEntity
}