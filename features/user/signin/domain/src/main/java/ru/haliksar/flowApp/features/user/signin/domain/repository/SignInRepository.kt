package ru.haliksar.flowApp.features.user.signin.domain.repository

import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity

interface SignInRepository {

    suspend fun signIn(data: SignInEntity): AuthEntity
}