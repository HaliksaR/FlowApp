package ru.haliksar.flowApp.features.user.accounts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserDto
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserMapperDto
import ru.haliksar.flowApp.features.user.accounts.domain.transaction.SortTransaction
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.AddUserUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.DeleteAllUsersUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.DeleteUserUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUsersUseCase
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperDto
import ru.haliksar.flowapp.libraries.core.data.mapper.toListDto
import ru.haliksar.flowapp.libraries.core.domain.useCase

@KoinApiExtension
class AccountsViewModel : ViewModel(), KoinComponent {

    private val getUsersUseCase by useCase<GetUsersUseCase>()
    private val addUserUseCase by useCase<AddUserUseCase>()
    private val deleteUserUseCase by useCase<DeleteUserUseCase>()
    private val deleteAllUsersUseCase by useCase<DeleteAllUsersUseCase>()
    private val sortTransaction by useCase<SortTransaction>()
    private val userMapper by mapperDto<UserMapperDto>()

    private val transactionDataFlow = sortTransaction(Unit)

    val byName = transactionDataFlow.byName.map {
        it.map { userMapper.toDto(it) }
    }
    val bySurname = transactionDataFlow.bySurname.map {
        it.map { userMapper.toDto(it) }
    }
    val byAge = transactionDataFlow.byAge.map {
        it.map { userMapper.toDto(it) }
    }
    val byLogin = transactionDataFlow.byLogin.map {
        it.map { userMapper.toDto(it) }
    }

    @ExperimentalCoroutinesApi
    val usersFlow = getUsersUseCase(Unit)
        .toListDto(userMapper)
        .onEach { users = it }

    private var users = listOf<UserDto>()

    private fun generateUser(): UserDto {
        val id = (1..100000).random()
        val name = getRandomString(8)
        val surname = getRandomString(6)
        val login = getRandomString(10)
        val age = (1..100).random()
        return UserDto(id = id, name = name, surname = surname, age = age, login = login)
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            if (users.isNotEmpty()) {
                deleteUserUseCase(userMapper.toEntity(users.last()).id)
            }
        }
    }

    fun add() {
        viewModelScope.launch(Dispatchers.IO) {
            addUserUseCase(userMapper.toEntity(generateUser()))
        }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllUsersUseCase(Unit)
        }
    }
}