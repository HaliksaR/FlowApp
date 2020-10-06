package ru.haliksar.flowApp.features.user.signin.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

class SignInUseCase(private val repository: SignInRepository) {

    operator fun invoke(data: SignInEntity): Flow<NetworkResponse<out AuthEntity>> =
        repository.signIn(data)
}