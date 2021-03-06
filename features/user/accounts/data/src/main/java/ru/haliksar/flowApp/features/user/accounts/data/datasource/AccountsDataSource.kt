package ru.haliksar.flowApp.features.user.accounts.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity

interface AccountsDataSource {
    fun getUsers(): Flow<List<UserEntity>>
    fun getUserSortedByName(): Flow<List<UserEntity>>
    fun getUserSortedBySurname(): Flow<List<UserEntity>>
    fun getUserSortedByAge(): Flow<List<UserEntity>>
    fun getUserSortedByLogin(): Flow<List<UserEntity>>
    suspend fun delete(id: Int)
    suspend fun add(user: UserEntity)
    suspend fun deleteAll()
}