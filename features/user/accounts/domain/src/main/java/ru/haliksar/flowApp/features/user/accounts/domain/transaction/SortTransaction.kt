package ru.haliksar.flowApp.features.user.accounts.domain.transaction

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedByAgeUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedByLoginUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedByNameUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUserSortedBySurnameUseCase
import ru.haliksar.flowapp.libraries.core.domain.UseCase


data class SortTransactionData(
    val byAge: Flow<List<UserEntity>>,
    val byName: Flow<List<UserEntity>>,
    val bySurname: Flow<List<UserEntity>>,
    val byLogin: Flow<List<UserEntity>>,
)

interface SortTransaction : UseCase<SortTransactionData, Unit>

class SortTransactionImpl(
    private val byAgeUseCase: GetUserSortedByAgeUseCase,
    private val byNameUseCase: GetUserSortedByNameUseCase,
    private val bySurnameUseCase: GetUserSortedBySurnameUseCase,
    private val byLoginUseCase: GetUserSortedByLoginUseCase,
) : SortTransaction {

    override fun invoke(param: Unit): SortTransactionData =
        SortTransactionData(
            byAge = byAgeUseCase(Unit),
            byName = byNameUseCase(Unit),
            bySurname = bySurnameUseCase(Unit),
            byLogin = byLoginUseCase(Unit)
        )
}