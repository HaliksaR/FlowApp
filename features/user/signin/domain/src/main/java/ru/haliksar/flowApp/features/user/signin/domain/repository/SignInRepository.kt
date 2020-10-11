package ru.haliksar.flowApp.features.user.signin.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

interface SignInRepository {

    fun signIn(data: SignInEntity): Flow<NetResponse<AuthEntity>>
}