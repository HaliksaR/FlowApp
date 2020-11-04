package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository

class DeleteUserUseCase(private val repository: AccountsRepository) {

    suspend operator fun invoke(param: Int): Unit = repository.delete(param)
}