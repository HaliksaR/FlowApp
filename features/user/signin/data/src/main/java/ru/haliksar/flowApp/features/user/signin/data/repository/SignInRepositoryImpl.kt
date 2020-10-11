package ru.haliksar.flowApp.features.user.signin.data.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.data.datasource.SignInDataSource
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

class SignInRepositoryImpl(private val dataSource: SignInDataSource) : SignInRepository {

    override fun signIn(data: SignInEntity): Flow<NetResponse<AuthEntity>> =
        dataSource.signIn(data = data)
}