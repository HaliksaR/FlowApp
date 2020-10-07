package ru.haliksar.flowApp.features.user.signin.presentation.uistate

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthUiData
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException

sealed class UiState {
    object Input : UiState()
    object Loading : UiState()
    data class Success(val data: AuthUiData) : UiState()
    data class Error(val error: NetworkException) : UiState()
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.input() {
    value = UiState.Input
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.loading() {
    value = UiState.Loading
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.success(data: AuthUiData) {
    value = UiState.Success(data)
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.error(error: NetworkException) {
    value = UiState.Error(error)
}
