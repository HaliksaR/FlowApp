package ru.haliksar.flowApp.features.user.accounts.injector

import ru.haliksar.flowApp.features.user.accounts.data.di.AccountsDataModules
import ru.haliksar.flowApp.features.user.accounts.domain.di.AccountsDomainModules
import ru.haliksar.flowApp.features.user.accounts.presentation.di.AccountsPresentationModules

val AccountsModules =
    listOf(AccountsPresentationModules) + AccountsDataModules + AccountsDomainModules