package ru.haliksar.flowApp.features.user.signin.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase
import ru.haliksar.flowApp.features.user.signin.presentation.mapper.toEntity
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.UiState
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.error
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.loading
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.success
import ru.haliksar.flowapp.libraries.core.presentation.base.StateViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.toNetworkException

@ExperimentalCoroutinesApi
@KoinApiExtension
class SignInViewModel : StateViewModel<UiState>(), KoinComponent {

    override val uiState = MutableStateFlow<UiState>(UiState.Input)

    private val useCase by inject<SignInUseCase>()

    val signInData = SignInUiData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                signInData.login.value = getRandomString(10)
                delay(1000)
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
        if (signInData.validate()) {
            uiState.loading()
            viewModelScope.launch {
                try {
                    uiState.success(useCase(signInData.toEntity()))
                } catch (throwable: Throwable) {
                    uiState.error(throwable.toNetworkException())
                }
            }
        }
    }
}