package ru.haliksar.flowApp.features.user.signin.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

typealias SignInUseCaseT = UseCase<Flow<NetworkResponse<out AuthEntity>>, SignInEntity>

class SignInUseCase(private val repository: SignInRepository) : SignInUseCaseT {

    override fun invoke(param: SignInEntity): Flow<NetworkResponse<out AuthEntity>> =
        repository.signIn(param)
}