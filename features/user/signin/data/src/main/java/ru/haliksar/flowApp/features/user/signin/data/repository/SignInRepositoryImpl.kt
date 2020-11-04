package ru.haliksar.flowApp.features.user.signin.data.repository

import ru.haliksar.flowApp.features.user.signin.data.datasource.SignInDataSource
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository

class SignInRepositoryImpl(private val dataSource: SignInDataSource) : SignInRepository {

    override suspend fun signIn(data: SignInEntity): AuthEntity =
        dataSource.signIn(data = data)
}