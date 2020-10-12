package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase

interface GetUserSortedByAgeUseCase : UseCase<Flow<List<UserEntity>>, Unit>

class GetUserSortedByAgeUseCaseImpl(private val repository: AccountsRepository) :
    GetUserSortedByAgeUseCase {

    override fun invoke(param: Unit): Flow<List<UserEntity>> =
        repository.getUserSortedByAge()
}