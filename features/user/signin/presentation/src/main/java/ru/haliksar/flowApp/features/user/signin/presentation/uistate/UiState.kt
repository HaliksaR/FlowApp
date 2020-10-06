package ru.haliksar.flowApp.features.user.signin.presentation.uistate

import androidx.lifecycle.MutableLiveData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthUiData
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException

sealed class UiState {
    object Input : UiState()
    object Loading : UiState()
    data class Success(val data: AuthUiData) : UiState()
    data class Error(val error: NetworkException) : UiState()
}

internal fun MutableLiveData<UiState>.input() =
    postValue(UiState.Input)

internal fun MutableLiveData<UiState>.loading() =
    postValue(UiState.Loading)

internal fun MutableLiveData<UiState>.success(data: AuthUiData) =
    postValue(UiState.Success(data))

internal fun MutableLiveData<UiState>.error(error: NetworkException) =
    postValue(UiState.Error(error))