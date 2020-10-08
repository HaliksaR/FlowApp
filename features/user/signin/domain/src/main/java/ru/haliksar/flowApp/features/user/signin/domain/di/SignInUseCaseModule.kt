package ru.haliksar.flowApp.features.user.signin.domain.di

import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCaseT

val SignInUseCaseModule = module {
    factory<SignInUseCaseT> { SignInUseCase(get()) }
}