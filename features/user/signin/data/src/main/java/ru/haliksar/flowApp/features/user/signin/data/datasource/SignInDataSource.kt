package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInDto

interface SignInDataSource {
    fun signIn(data: SignInDto): Flow<AuthDto>
}