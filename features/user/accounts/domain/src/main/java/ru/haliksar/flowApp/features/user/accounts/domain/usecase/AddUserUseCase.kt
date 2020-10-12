package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase

interface AddUserUseCase : UseCase<Unit, UserEntity>

class AddUserUseCaseImpl(private val repository: AccountsRepository) : AddUserUseCase {

    override fun invoke(param: UserEntity): Unit =
        repository.add(param)
}