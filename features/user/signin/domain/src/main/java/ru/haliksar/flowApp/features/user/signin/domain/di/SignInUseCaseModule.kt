package ru.haliksar.flowApp.features.user.signin.domain.di

import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase

val SignInUseCaseModule = module {
    factory { SignInUseCase(get()) }
}