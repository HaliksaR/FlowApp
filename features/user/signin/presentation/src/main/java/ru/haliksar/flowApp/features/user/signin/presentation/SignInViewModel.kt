package ru.haliksar.flowApp.features.user.signin.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.UiState
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.error
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.loading
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.success
import ru.haliksar.flowapp.libraries.core.presentation.BaseViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

@ExperimentalCoroutinesApi
@KoinApiExtension
class SignInViewModel : BaseViewModel() {

    private val useCase by inject<SignInUseCase>()

    private val uiState = MutableStateFlow<UiState>(UiState.Input)

    private val signInMapper by inject<SignInMapperUiData>()

    private val authMapper by inject<AuthMapperUiData>()

    val loginFlow = MutableStateFlow("")
    val passwordFlow = MutableStateFlow("")

    fun uiStateObserve(scope: CoroutineScope, action: (UiState) -> Unit) =
        uiState.onEach { action(it) }.launchIn(scope)

    init {
        loginFlow.onEach {
            Log.d("twoWayFlow", it)
        }.launchIn(viewModelScope)
        loginFlow.value = "twoWayFlow"
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                loginFlow.value = getRandomString(10)
                delay(10000)
            }
        }
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun startSignIn() {
        if (loginFlow.value.isNotBlank() && passwordFlow.value.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                useCase(
                    signInMapper.toEntity(
                        SignInUiData(loginFlow.value, passwordFlow.value)
                    )
                ).collect {
                    when (it) {
                        NetworkResponse.Loading -> uiState.loading()
                        is NetworkResponse.Success -> uiState.success(authMapper.toUiData(it.data))
                        is NetworkResponse.Error -> uiState.error(it.exception)
                    }
                }
            }
        }
    }
}