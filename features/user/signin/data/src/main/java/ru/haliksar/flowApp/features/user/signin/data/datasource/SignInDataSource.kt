package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

interface SignInDataSource {
    fun signIn(data: SignInEntity): Flow<NetResponse<AuthEntity>>
}