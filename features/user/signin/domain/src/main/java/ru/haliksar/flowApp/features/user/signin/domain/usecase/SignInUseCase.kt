package ru.haliksar.flowApp.features.user.signin.domain.usecase

import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository

class SignInUseCase(private val repository: SignInRepository) {

    suspend operator fun invoke(param: SignInEntity): AuthEntity =
        repository.signIn(param)
}