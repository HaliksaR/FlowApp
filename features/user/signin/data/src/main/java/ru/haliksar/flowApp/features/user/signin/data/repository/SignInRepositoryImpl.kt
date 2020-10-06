package ru.haliksar.flowApp.features.user.signin.data.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.data.datasource.SignInDataSource
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow

class SignInRepositoryImpl(
    private val dataSource: SignInDataSource
) : SignInRepository {

    override fun signIn(data: SignInEntity): Flow<NetworkResponse<out AuthEntity>> =
        safeCallFlow { dataSource.signIn(data = data) }
}