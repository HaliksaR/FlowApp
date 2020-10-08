package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthMapperDtoT
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInMapperDtoT
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.mapperDto
import ru.haliksar.flowapp.libraries.core.data.toEntity

@KoinApiExtension
class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource, KoinComponent {

    private val mapperSignIn by mapperDto<SignInMapperDtoT>()
    private val mapperAuth by mapperDto<AuthMapperDtoT>()

    override fun signIn(data: SignInEntity): Flow<AuthEntity> =
        api.signIn(mapperSignIn.toDto(data))
            .toEntity(mapperAuth)
}