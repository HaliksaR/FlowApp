package ru.haliksar.flowApp.features.user.accounts.presentation.di.injector

import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowApp.features.user.accounts.presentation.di.AccountsModules
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector

@KoinApiExtension
class AccountsDynamicInjector : DynamicInjector(AccountsModules)