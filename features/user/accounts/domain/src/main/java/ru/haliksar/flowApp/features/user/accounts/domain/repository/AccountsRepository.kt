package ru.haliksar.flowApp.features.user.accounts.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity

interface AccountsRepository {
    fun getUsers(): Flow<List<UserEntity>>
    fun getUserSortedByName(): Flow<List<UserEntity>>
    fun getUserSortedBySurname(): Flow<List<UserEntity>>
    fun getUserSortedByAge(): Flow<List<UserEntity>>
    fun getUserSortedByLogin(): Flow<List<UserEntity>>
    suspend fun delete(id: Int)
    suspend fun add(user: UserEntity)
    suspend fun deleteAll()
}