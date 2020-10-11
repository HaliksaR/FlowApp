package ru.haliksar.flowApp.features.user.signin.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowApp.features.user.signin.domain.di.SIGN_IN_USECASE
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCaseT
import ru.haliksar.flowApp.features.user.signin.presentation.di.AUTH_MAPPER_UIDATA
import ru.haliksar.flowApp.features.user.signin.presentation.di.SIGN_IN_MAPPER_UIDATA
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiDataT
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiDataT
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.UiState
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.error
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.loading
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.success
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperUiData
import ru.haliksar.flowapp.libraries.core.domain.useCase
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

@ExperimentalCoroutinesApi
@KoinApiExtension
class SignInViewModel : BaseViewModel<UiState>() {

    override val uiState = MutableStateFlow<UiState>(UiState.Input)

    private val useCase by useCase<SignInUseCaseT>(named(SIGN_IN_USECASE))

    private val signInMapper
            by mapperUiData<SignInMapperUiDataT>(named(SIGN_IN_MAPPER_UIDATA))

    private val authMapper
            by mapperUiData<AuthMapperUiDataT>(named(AUTH_MAPPER_UIDATA))

    val loginFlow = MutableStateFlow("")
    val passwordFlow = MutableStateFlow("")

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
            uiState.loading()
            viewModelScope.launch(Dispatchers.IO) {
                useCase(
                    signInMapper.toEntity(
                        SignInUiData(loginFlow.value, passwordFlow.value)
                    )
                ).collect {
                    when (it) {
                        is NetworkResponse.Success -> uiState.success(authMapper.toUiData(it.data))
                        is NetworkResponse.Error -> uiState.error(it.exception)
                    }
                }
            }
        }
    }
}