package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository

class DeleteAllUsersUseCase(private val repository: AccountsRepository) {

    suspend operator fun invoke(): Unit = repository.deleteAll()
}