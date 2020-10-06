package ru.haliksar.flowApp.features.user.signin.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowApp.features.user.signin.data.datasource.SignInDataSource
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthMapperDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInMapperDto
import ru.haliksar.flowApp.features.user.signin.domain.entity.AuthEntity
import ru.haliksar.flowApp.features.user.signin.domain.entity.SignInEntity
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.network.wrappers.dataToEntity
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow

@KoinApiExtension
class SignInRepositoryImpl(
    private val dataSource: SignInDataSource
) : SignInRepository, KoinComponent {

    private val mapperSignIn by inject<SignInMapperDto>()

    private val mapperAuth by inject<AuthMapperDto>()

    override fun signIn(data: SignInEntity): Flow<NetworkResponse<out AuthEntity>> =
        safeCallFlow { dataSource.signIn(data = mapperSignIn.toDto(data)) }.map {
            it.dataToEntity(mapperAuth)
        }
}