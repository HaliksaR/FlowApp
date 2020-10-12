package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase

interface GetUsersUseCase : UseCase<Flow<List<UserEntity>>, Unit>

class GetUsersUseCaseImpl(private val repository: AccountsRepository) : GetUsersUseCase {

    override fun invoke(param: Unit): Flow<List<UserEntity>> =
        repository.getUsers()
}