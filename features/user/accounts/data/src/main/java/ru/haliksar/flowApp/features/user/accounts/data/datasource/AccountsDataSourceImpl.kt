package ru.haliksar.flowApp.features.user.accounts.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.haliksar.flowApp.features.user.accounts.data.dao.AccountsDao
import ru.haliksar.flowApp.features.user.accounts.data.mapper.toDto
import ru.haliksar.flowApp.features.user.accounts.data.mapper.toListEntity
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity

class AccountsDataSourceImpl(
    private val dao: AccountsDao
) : AccountsDataSource {

    override fun getUsers(): Flow<List<UserEntity>> =
        dao.getAll().map { it.toListEntity() }
            .flowOn(Dispatchers.IO)

    override fun getUserSortedByName(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.name }.toListEntity() }
            .flowOn(Dispatchers.IO)

    override fun getUserSortedBySurname(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.surname }.toListEntity() }
            .flowOn(Dispatchers.IO)

    override fun getUserSortedByAge(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.age }.toListEntity() }
            .flowOn(Dispatchers.IO)

    override fun getUserSortedByLogin(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.login }.toListEntity() }
            .flowOn(Dispatchers.IO)

    override suspend fun delete(id: Int) =
        withContext(Dispatchers.IO) {
            dao.delete(id)
        }

    override suspend fun add(user: UserEntity) =
        withContext(Dispatchers.IO) {
            dao.insert(user.toDto())
        }

    override suspend fun deleteAll() =
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
}