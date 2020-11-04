package ru.haliksar.flowApp.features.user.signin.domain.di

import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase

internal val SignInUseCaseModule = module {
    factory { SignInUseCase(get()) }
}

val SignInDomainModules = listOf(
    SignInUseCaseModule
)