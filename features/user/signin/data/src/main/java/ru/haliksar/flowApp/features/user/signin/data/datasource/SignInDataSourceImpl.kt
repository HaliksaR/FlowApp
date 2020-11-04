package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowApp.features.user.signin.data.mapper.toDto
import ru.haliksar.flowApp.features.user.signin.data.mapper.toEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity

class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource {

    override suspend fun signIn(data: SignInEntity): AuthEntity =
        withContext(Dispatchers.IO) {
            api.signIn(data.toDto()).toEntity()
        }
}