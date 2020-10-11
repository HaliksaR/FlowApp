package ru.haliksar.flowApp.features.user.signin.presentation.uidata

import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.MapperUiData

data class SignInUiData(
    var login: String,
    var password: String
)

typealias SignInMapperUiDataT = MapperUiData<SignInEntity, SignInUiData>

class SignInMapperUiData : SignInMapperUiDataT() {

    override fun toUiData(entity: SignInEntity): SignInUiData =
        SignInUiData(entity.login, entity.password)

    override fun toEntity(uiData: SignInUiData): SignInEntity =
        SignInEntity(uiData.login, uiData.password)
}