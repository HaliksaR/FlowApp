package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInDto

class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource {

    override fun signIn(data: SignInDto): Flow<AuthDto> = api.signIn(data)
}