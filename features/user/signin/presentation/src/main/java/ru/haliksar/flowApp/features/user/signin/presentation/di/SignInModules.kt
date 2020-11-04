package ru.haliksar.flowApp.features.user.signin.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.data.di.SignInDataModules
import ru.haliksar.flowApp.features.user.signin.domain.di.SignInDomainModules
import ru.haliksar.flowApp.features.user.signin.presentation.SignInViewModel

@OptIn(KoinApiExtension::class)
internal val viewModelModule = module { viewModel { SignInViewModel() } }

val SignInModules = listOf(viewModelModule) + SignInDataModules + SignInDomainModules