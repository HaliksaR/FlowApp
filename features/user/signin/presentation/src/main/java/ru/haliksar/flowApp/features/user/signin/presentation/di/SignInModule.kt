package ru.haliksar.flowApp.features.user.signin.presentation.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.presentation.SignInViewModel
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiData

@OptIn(KoinApiExtension::class)
@ExperimentalCoroutinesApi
val SignInViewModelModule = module {
    viewModel { SignInViewModel() }
}

val SignInDataMappersModule = module {
    factory { AuthMapperUiData() }
    factory { SignInMapperUiData() }
}