package ru.haliksar.flowApp.features.user.accounts.domain.scenario

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedByAgeFlowUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedByLoginFlowUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedByNameFlowUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedBySurnameFlowUseCase


data class SortTransactionData(
    val byAge: Flow<List<UserEntity>>,
    val byName: Flow<List<UserEntity>>,
    val bySurname: Flow<List<UserEntity>>,
    val byLogin: Flow<List<UserEntity>>,
)

class SortFlowScenario(
    private val byAgeFlowUseCase: GetUserSortedByAgeFlowUseCase,
    private val byNameFlowUseCase: GetUserSortedByNameFlowUseCase,
    private val bySurnameFlowUseCase: GetUserSortedBySurnameFlowUseCase,
    private val byLoginFlowUseCase: GetUserSortedByLoginFlowUseCase,
) {

    operator fun invoke(): SortTransactionData =
        SortTransactionData(
            byAge = byAgeFlowUseCase(),
            byName = byNameFlowUseCase(),
            bySurname = bySurnameFlowUseCase(),
            byLogin = byLoginFlowUseCase()
        )
}