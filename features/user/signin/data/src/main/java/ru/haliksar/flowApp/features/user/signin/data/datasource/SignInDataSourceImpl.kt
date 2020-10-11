package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowApp.features.user.signin.data.di.AUTH_MAPPER_DTO
import ru.haliksar.flowApp.features.user.signin.data.di.SIGN_IN_MAPPER_DTO
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthMapperDtoT
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInMapperDtoT
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperDto
import ru.haliksar.flowapp.libraries.core.data.mapper.toEntity
import ru.haliksar.flowapp.libraries.network.wrappers.NetResponse
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow

@KoinApiExtension
class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource, KoinComponent {

    private val mapperSignIn by mapperDto<SignInMapperDtoT>(
        named(SIGN_IN_MAPPER_DTO)
    )
    private val mapperAuth by mapperDto<AuthMapperDtoT>(
        named(AUTH_MAPPER_DTO)
    )

    override fun signIn(data: SignInEntity): Flow<NetResponse<AuthEntity>> =
        safeCallFlow { api.signIn(mapperSignIn.toDto(data)).toEntity(mapperAuth) }

}