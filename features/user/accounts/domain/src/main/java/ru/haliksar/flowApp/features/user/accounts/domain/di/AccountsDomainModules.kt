package ru.haliksar.flowApp.features.user.accounts.domain.di

import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.accounts.domain.scenario.SortFlowScenario
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.*


internal val AccountsDomainModuleUseCase = module {
    factory { AddUserUseCase(get()) }
    factory { DeleteAllUsersUseCase(get()) }
    factory { DeleteUserUseCase(get()) }

    factory { GetUserSortedByAgeFlowUseCase(get()) }
    factory { GetUserSortedByNameFlowUseCase(get()) }
    factory { GetUserSortedBySurnameFlowUseCase(get()) }
    factory { GetUserSortedByLoginFlowUseCase(get()) }
    factory { GetUsersFlowUseCase(get()) }
}

internal val AccountsDomainModuleScenario = module {
    factory { SortFlowScenario(get(), get(), get(), get()) }
}

val AccountsDomainModules = listOf(
    AccountsDomainModuleUseCase,
    AccountsDomainModuleScenario
)