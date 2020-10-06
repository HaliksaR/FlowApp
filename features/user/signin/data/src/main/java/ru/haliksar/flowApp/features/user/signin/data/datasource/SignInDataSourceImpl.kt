package ru.haliksar.flowApp.features.user.signin.data.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthMapperDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInMapperDto
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowapp.libraries.core.data.toEntity

@KoinApiExtension
class SignInDataSourceImpl(private val api: SignInApi) : SignInDataSource, KoinComponent {

    private val mapperSignIn by inject<SignInMapperDto>()
    private val mapperAuth by inject<AuthMapperDto>()

    override fun signIn(data: SignInEntity): Flow<AuthEntity> =
        api.signIn(mapperSignIn.toDto(data))
            .toEntity(mapperAuth)
}