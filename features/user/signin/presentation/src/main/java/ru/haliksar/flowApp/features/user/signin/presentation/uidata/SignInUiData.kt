package ru.haliksar.flowApp.features.user.signin.presentation.uidata

import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.MapperUiData

data class SignInUiData(
    var login: String,
    var password: String
)

class SignInMapperUiData : MapperUiData<SignInEntity, SignInUiData>() {

    override fun toUiData(entity: SignInEntity): SignInUiData =
        SignInUiData(entity.login, entity.password)

    override fun toEntity(uiData: SignInUiData): SignInEntity =
        SignInEntity(uiData.login, uiData.password)
}