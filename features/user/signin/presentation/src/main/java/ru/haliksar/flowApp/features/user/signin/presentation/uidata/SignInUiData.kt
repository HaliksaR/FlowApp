package ru.haliksar.flowApp.features.user.signin.presentation.uidata

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.MapperUiData

@ExperimentalCoroutinesApi
data class SignInUiData(
    val login: MutableStateFlow<String> = MutableStateFlow(""),
    val password: MutableStateFlow<String> = MutableStateFlow("")
) {
    fun validate(): Boolean =
        login.value.isNotBlank() && password.value.isNotBlank()
}

@OptIn(ExperimentalCoroutinesApi::class)
typealias SignInMapperUiDataT = MapperUiData<SignInEntity, SignInUiData>

@ExperimentalCoroutinesApi
class SignInMapperUiData : SignInMapperUiDataT() {

    @ExperimentalCoroutinesApi
    override fun toUiData(entity: SignInEntity): SignInUiData =
        SignInUiData(
            login = MutableStateFlow(entity.login),
            password = MutableStateFlow(entity.password),
        )

    override fun toEntity(uiData: SignInUiData): SignInEntity =
        SignInEntity(
            login = uiData.login.value,
            password = uiData.password.value
        )
}