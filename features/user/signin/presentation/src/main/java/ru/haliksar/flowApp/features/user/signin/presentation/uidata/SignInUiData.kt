package ru.haliksar.flowApp.features.user.signin.presentation.uidata

import kotlinx.coroutines.flow.MutableStateFlow

data class SignInUiData(
    val login: MutableStateFlow<String> = MutableStateFlow(""),
    val password: MutableStateFlow<String> = MutableStateFlow("")
) {
    fun validate(): Boolean =
        login.value.isNotBlank() && password.value.isNotBlank()
}