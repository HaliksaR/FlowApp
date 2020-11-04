package ru.haliksar.flowApp.features.user.signin.presentation.uistate

import kotlinx.coroutines.flow.MutableStateFlow
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException

sealed class UiState {
    object Input : UiState()
    object Loading : UiState()
    data class Success(val data: AuthEntity) : UiState()
    data class Error(val error: NetworkException) : UiState()
}

internal fun MutableStateFlow<UiState>.input() {
    value = UiState.Input
}

internal fun MutableStateFlow<UiState>.loading() {
    value = UiState.Loading
}

internal fun MutableStateFlow<UiState>.success(data: AuthEntity) {
    value = UiState.Success(data)
}

internal fun MutableStateFlow<UiState>.error(error: NetworkException) {
    value = UiState.Error(error)
}
