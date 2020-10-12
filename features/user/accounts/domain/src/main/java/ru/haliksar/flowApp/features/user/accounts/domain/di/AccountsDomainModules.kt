package ru.haliksar.flowApp.features.user.accounts.domain.di

import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.accounts.domain.transaction.SortTransaction
import ru.haliksar.flowApp.features.user.accounts.domain.transaction.SortTransactionImpl
import ru.haliksar.flowApp.features.user.accounts.domain.usecase.*


internal val AccountsDomainModuleUseCase = module {
    factory<AddUserUseCase> { AddUserUseCaseImpl(get()) }
    factory<DeleteAllUsersUseCase> { DeleteAllUsersUseCaseImpl(get()) }
    factory<DeleteUserUseCase> { DeleteUserUseCaseImpl(get()) }
    factory<GetUserSortedByAgeUseCase> { GetUserSortedByAgeUseCaseImpl(get()) }
    factory<GetUserSortedByNameUseCase> { GetUserSortedByNameUseCaseImpl(get()) }
    factory<GetUserSortedBySurnameUseCase> { GetUserSortedBySurnameUseCaseImpl(get()) }
    factory<GetUserSortedByLoginUseCase> { GetUserSortedByLoginUseCaseImpl(get()) }
    factory<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
}

internal val AccountsDomainModuleTransaction = module {
    factory<SortTransaction> { SortTransactionImpl(get(), get(), get(), get()) }
}

val AccountsDomainModules = listOf(
    AccountsDomainModuleUseCase,
    AccountsDomainModuleTransaction
)