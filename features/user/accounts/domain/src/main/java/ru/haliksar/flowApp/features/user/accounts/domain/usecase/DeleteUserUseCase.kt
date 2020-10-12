package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase

interface DeleteUserUseCase : UseCase<Unit, Int>

class DeleteUserUseCaseImpl(private val repository: AccountsRepository) : DeleteUserUseCase {

    override fun invoke(param: Int): Unit =
        repository.delete(param)
}