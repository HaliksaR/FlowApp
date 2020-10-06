package ru.haliksar.flowApp.features.user.signin.presentation.uidata

import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowapp.libraries.core.data.MapperUiData

data class AuthUiData(
    val accessToken: String
)

class AuthMapperUiData : MapperUiData<AuthEntity, AuthUiData>() {

    override fun toUiData(entity: AuthEntity): AuthUiData =
        AuthUiData(entity.accessToken)

    override fun toEntity(uiData: AuthUiData): AuthEntity =
        AuthEntity(uiData.accessToken)
}