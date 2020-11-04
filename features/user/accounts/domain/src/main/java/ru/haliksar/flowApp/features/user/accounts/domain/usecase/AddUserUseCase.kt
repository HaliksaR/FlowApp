package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository

class AddUserUseCase(private val repository: AccountsRepository) {

    suspend operator fun invoke(param: UserEntity): Unit = repository.add(param)
}