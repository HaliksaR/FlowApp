package ru.haliksar.flowApp.features.user.signin.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.*
import ru.haliksar.flowapp.libraries.core.presentation.BaseViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

@KoinApiExtension
class SignInViewModel : BaseViewModel() {

    private val useCase by inject<SignInUseCase>()

    private val uiState = MutableLiveData<UiState>().apply {
        input()
    }

    private val signInMapper by inject<SignInMapperUiData>()

    private val authMapper by inject<AuthMapperUiData>()

    private val testData = SignInUiData("", "")

    fun uiStateObserve(owner: LifecycleOwner, action: (UiState) -> Unit) =
        uiState.observe(owner, Observer { action(it) })

    fun setLogin(login: String) {
        uiState.input()
        testData.login = login
    }

    fun setPassword(password: String) {
        uiState.input()
        testData.password = password
    }

    fun startSignIn() {
        if (testData.login.isNotEmpty() && testData.password.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                useCase(signInMapper.toEntity(testData))
                    .collect {
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