package ru.haliksar.flowApp.features.user.signin.data.dto

import com.google.gson.annotations.SerializedName
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto

data class AuthDto(
    @SerializedName("accessToken") val accessToken: String
)

typealias AuthMapperDtoT = MapperDto<AuthEntity, AuthDto>

class AuthMapperDto : AuthMapperDtoT() {

    override fun toDto(entity: AuthEntity): AuthDto =
        AuthDto(entity.accessToken)

    override fun toEntity(dto: AuthDto): AuthEntity =
        AuthEntity(dto.accessToken)
}