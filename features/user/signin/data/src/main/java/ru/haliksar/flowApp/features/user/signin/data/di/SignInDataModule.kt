package ru.haliksar.flowApp.features.user.signin.data.di

import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.data.api.SignInApi
import ru.haliksar.flowApp.features.user.signin.data.datasource.SignInDataSource
import ru.haliksar.flowApp.features.user.signin.data.datasource.SignInDataSourceImpl
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthMapperDto
import ru.haliksar.flowApp.features.user.signin.data.dto.AuthMapperDtoT
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInMapperDto
import ru.haliksar.flowApp.features.user.signin.data.dto.SignInMapperDtoT
import ru.haliksar.flowApp.features.user.signin.data.repository.SignInRepositoryImpl
import ru.haliksar.flowApp.features.user.signin.domain.repository.SignInRepository
import ru.haliksar.flowapp.libraries.network.createRetrofitService
import ru.haliksar.flowapp.libraries.network.di.FAKE

internal val SignInApiModule = module {
    factory<SignInApi> { createRetrofitService(get(named(FAKE))) }
}

internal val SignInDataMappersModule = module {
    factory<AuthMapperDtoT> { AuthMapperDto() }
    factory<SignInMapperDtoT> { SignInMapperDto() }
}

@OptIn(KoinApiExtension::class)
internal val SignInDataSourceModule = module {
    factory<SignInDataSource> { SignInDataSourceImpl(get()) }
}

internal val SignInRepositoryModule = module {
    factory<SignInRepository> { SignInRepositoryImpl(get()) }
}

val SignInDataModules = listOf(
    SignInApiModule,
    SignInDataMappersModule,
    SignInDataSourceModule,
    SignInRepositoryModule
)