package ru.haliksar.flowApp.features.user.signin.data.dto

import com.google.gson.annotations.SerializedName
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto

data class SignInDto(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)

class SignInMapperDto : MapperDto<SignInEntity, SignInDto>() {

    override fun toDto(entity: SignInEntity): SignInDto =
        SignInDto(entity.login, entity.password)

    override fun toEntity(dto: SignInDto): SignInEntity =
        SignInEntity(dto.login, dto.password)
}