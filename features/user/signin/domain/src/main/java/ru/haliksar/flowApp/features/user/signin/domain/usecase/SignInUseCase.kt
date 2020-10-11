package ru.haliksar.flowApp.features.user.signin.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse

typealias SignInUseCaseT = UseCase<Flow<NetResponse<AuthEntity>>, SignInEntity>

class SignInUseCase(private val repository: SignInRepository) : SignInUseCaseT {

    override fun invoke(param: SignInEntity): Flow<NetResponse<AuthEntity>> =
        repository.signIn(param)
}