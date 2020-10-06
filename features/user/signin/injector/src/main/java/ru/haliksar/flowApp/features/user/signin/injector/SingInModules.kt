package ru.haliksar.flowApp.features.user.signin.injector

import ru.haliksar.flowApp.features.user.signin.data.di.SignInDataModules
import ru.haliksar.flowApp.features.user.signin.domain.di.SignInUseCaseModule
import ru.haliksar.flowApp.features.user.signin.presentation.di.SignInDataMappersModule
import ru.haliksar.flowApp.features.user.signin.presentation.di.SignInViewModelModule

val SingInModules =
    listOf(SignInViewModelModule, SignInUseCaseModule, SignInDataMappersModule) + SignInDataModules