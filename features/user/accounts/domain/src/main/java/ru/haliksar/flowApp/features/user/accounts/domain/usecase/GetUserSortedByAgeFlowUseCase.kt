package ru.haliksar.flowApp.features.user.accounts.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository

class GetUserSortedByAgeFlowUseCase(private val repository: AccountsRepository) {

    operator fun invoke(): Flow<List<UserEntity>> = repository.getUserSortedByAge()
}