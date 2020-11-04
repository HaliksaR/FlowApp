package ru.haliksar.flowApp.features.user.accounts.data.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.data.datasource.AccountsDataSource
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository

class AccountsRepositoryImpl(
    private val dataSource: AccountsDataSource
) : AccountsRepository {

    override fun getUsers(): Flow<List<UserEntity>> =
        dataSource.getUsers()

    override fun getUserSortedByName(): Flow<List<UserEntity>> =
        dataSource.getUserSortedByName()

    override fun getUserSortedBySurname(): Flow<List<UserEntity>> =
        dataSource.getUserSortedBySurname()

    override fun getUserSortedByAge(): Flow<List<UserEntity>> =
        dataSource.getUserSortedByAge()

    override fun getUserSortedByLogin(): Flow<List<UserEntity>> =
        dataSource.getUserSortedByLogin()

    override suspend fun delete(id: Int) =
        dataSource.delete(id)

    override suspend fun add(user: UserEntity) =
        dataSource.add(user)

    override suspend fun deleteAll() =
        dataSource.deleteAll()
}