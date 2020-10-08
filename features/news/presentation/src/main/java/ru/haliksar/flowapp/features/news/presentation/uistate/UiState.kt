package ru.haliksar.flowapp.features.news.presentation.uistate

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException

sealed class UiState {
    object Nothing : UiState()
    object Loading : UiState()
    data class Success(val data: List<NewsUiData>) : UiState()
    data class Error(val error: NetworkException) : UiState()
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.nothing() {
    value = UiState.Nothing
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.loading() {
    value = UiState.Loading
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.success(data: List<NewsUiData>) {
    value = UiState.Success(data)
}

@ExperimentalCoroutinesApi
internal fun MutableStateFlow<UiState>.error(error: NetworkException) {
    value = UiState.Error(error)
}
