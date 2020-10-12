package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase

interface DeleteAllUsersUseCase : UseCase<Unit, Unit>

class DeleteAllUsersUseCaseImpl(private val repository: AccountsRepository) :
    DeleteAllUsersUseCase {

    override fun invoke(param: Unit): Unit =
        repository.deleteAll()
}