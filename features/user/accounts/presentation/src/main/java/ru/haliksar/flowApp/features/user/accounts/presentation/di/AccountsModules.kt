package ru.haliksar.flowApp.features.user.accounts.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.accounts.data.di.AccountsDataModules
import ru.haliksar.flowApp.features.user.accounts.domain.di.AccountsDomainModules
import ru.haliksar.flowApp.features.user.accounts.presentation.AccountsViewModel

@OptIn(KoinApiExtension::class)
internal val viewModelModule = module { viewModel { AccountsViewModel() } }

val AccountsModules = listOf(viewModelModule) + AccountsDataModules + AccountsDomainModules