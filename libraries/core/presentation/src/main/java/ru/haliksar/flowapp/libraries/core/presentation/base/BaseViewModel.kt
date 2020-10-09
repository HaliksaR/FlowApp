package ru.haliksar.flowapp.libraries.core.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
@ExperimentalCoroutinesApi
abstract class BaseViewModel<US> : ViewModel(), LifecycleObserver, KoinComponent {
    protected abstract val uiState: MutableStateFlow<US>

    private var uiStateJob: Job? = null

    open fun uiStateObserve(action: (US) -> Unit) {
        uiStateJob?.cancel()
        uiStateJob = uiState.onEach {
            action(it)
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        uiStateJob?.cancel()
    }
}