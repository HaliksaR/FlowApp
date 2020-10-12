package ru.haliksar.flowApp.features.user.accounts.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.accounts.data.dao.AccountsDao
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserMapperDto
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.toListEntity

@KoinApiExtension
class AccountsDataSourceImpl(
    private val dao: AccountsDao
) : AccountsDataSource, KoinComponent {

    private val mapper by inject<UserMapperDto>()

    override fun getUsers(): Flow<List<UserEntity>> =
        dao.getAll().toListEntity(mapper)

    override fun getUserSortedByName(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.name } }.toListEntity(mapper)

    override fun getUserSortedBySurname(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.surname } }.toListEntity(mapper)

    override fun getUserSortedByAge(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.age } }.toListEntity(mapper)

    override fun getUserSortedByLogin(): Flow<List<UserEntity>> =
        dao.getAll().map { it.sortedBy { it.login } }.toListEntity(mapper)

    override fun delete(id: Int) =
        dao.delete(id)

    override fun add(user: UserEntity) =
        dao.insert(mapper.toDto(user))

    override fun deleteAll() =
        dao.deleteAll()
}