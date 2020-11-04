package ru.haliksar.flowApp.features.user.accounts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowApp.features.user.accounts.domain.scenario.SortFlowScenario
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.AddUserUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.DeleteAllUsersUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.DeleteUserUseCase
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.GetUsersFlowUseCase

@KoinApiExtension
class AccountsViewModel : ViewModel(), KoinComponent {

    private val getUsersUseCase by inject<GetUsersFlowUseCase>()
    private val addUserUseCase by inject<AddUserUseCase>()
    private val deleteUserUseCase by inject<DeleteUserUseCase>()
    private val deleteAllUsersUseCase by inject<DeleteAllUsersUseCase>()
    private val sortFlowScenario by inject<SortFlowScenario>()

    private val sortFlow by lazy { sortFlowScenario() }
    val usersFlow = getUsersUseCase().onEach { users = it }

    val byName by lazy { sortFlow.byName }
    val bySurname by lazy { sortFlow.bySurname }
    val byAge by lazy { sortFlow.byAge }
    val byLogin by lazy { sortFlow.byLogin }

    private var users = listOf<UserEntity>()

    private fun generateUser(): UserEntity {
        val id = (1..100000).random()
        val name = getRandomString(8)
        val surname = getRandomString(6)
        val login = getRandomString(10)
        val age = (1..100).random()
        return UserEntity(id = id, name = name, surname = surname, age = age, login = login)
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun delete() {
        viewModelScope.launch {
            if (users.isNotEmpty()) {
                deleteUserUseCase(users.last().id)
            }
        }
    }

    fun add() {
        viewModelScope.launch {
            addUserUseCase(generateUser())
        }
    }

    fun clear() {
        viewModelScope.launch {
            deleteAllUsersUseCase()
        }
    }
}