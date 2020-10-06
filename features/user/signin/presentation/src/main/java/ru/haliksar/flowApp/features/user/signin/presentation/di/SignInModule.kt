package ru.haliksar.flowApp.features.user.signin.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.data.di.SignInDataModules
import ru.haliksar.flowApp.features.user.signin.domain.di.SignInUseCaseModule
import ru.haliksar.flowApp.features.user.signin.presentation.SignInViewModel
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiData

@OptIn(KoinApiExtension::class)
internal val SignInViewModelModule = module {
    viewModel { SignInViewModel() }
}

internal val SignInDataMappersModule = module {
    factory { AuthMapperUiData() }
    factory { SignInMapperUiData() }
}

val SignInModule =
    listOf(SignInViewModelModule, SignInUseCaseModule, SignInDataMappersModule) + SignInDataModules