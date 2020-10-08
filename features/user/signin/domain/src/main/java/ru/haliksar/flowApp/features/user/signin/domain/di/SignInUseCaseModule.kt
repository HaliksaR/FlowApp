package ru.haliksar.flowApp.features.user.signin.domain.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCase
import ru.haliksar.flowApp.features.user.signin.domain.usecase.SignInUseCaseT

const val SIGN_IN_USECASE = "SIGN_IN_USECASE"

val SignInUseCaseModule = module {
    factory<SignInUseCaseT>(named(SIGN_IN_USECASE)) { SignInUseCase(get()) }
}