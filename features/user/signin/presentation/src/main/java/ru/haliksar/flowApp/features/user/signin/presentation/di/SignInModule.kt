package ru.haliksar.flowApp.features.user.signin.presentation.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.signin.presentation.SignInViewModel
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.AuthMapperUiDataT
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiData
import ru.haliksar.flowApp.features.user.signin.presentation.uidata.SignInMapperUiDataT

@OptIn(KoinApiExtension::class)
@ExperimentalCoroutinesApi
val SignInViewModelModule = module {
    viewModel { SignInViewModel() }
}

const val AUTH_MAPPER_UIDATA = "AUTH_MAPPER_UIDATA "
const val SIGN_IN_MAPPER_UIDATA = "SIGN_IN_MAPPER_UIDATA"

@ExperimentalCoroutinesApi
val SignInDataMappersModule = module {
    factory<AuthMapperUiDataT>(named(AUTH_MAPPER_UIDATA)) { AuthMapperUiData() }
    factory<SignInMapperUiDataT>(named(SIGN_IN_MAPPER_UIDATA)) { SignInMapperUiData() }
}