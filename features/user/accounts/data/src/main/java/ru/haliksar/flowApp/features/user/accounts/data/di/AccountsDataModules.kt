package ru.haliksar.flowApp.features.user.accounts.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.accounts.data.dao.AccountsDao
import ru.haliksar.flowApp.features.user.accounts.data.database.AccountDatabase
import ru.haliksar.flowApp.features.user.accounts.data.database.AccountDatabase.Companion.NAME
import ru.haliksar.flowApp.features.user.accounts.data.datasource.AccountsDataSource
import ru.haliksar.flowApp.features.user.accounts.data.datasource.AccountsDataSourceImpl
import ru.haliksar.flowApp.features.user.accounts.data.repository.AccountsRepositoryImpl
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository


internal val AccountsDataDatabaseModule = module {
    single<AccountDatabase> {
        Room.databaseBuilder(androidContext(), AccountDatabase::class.java, NAME).build()
    }
}

internal val AccountsDataDaoModule = module {
    single<AccountsDao> { get<AccountDatabase>().accountsDao() }
}

internal val AccountsDataDataSourceModule = module {
    factory<AccountsDataSource> { AccountsDataSourceImpl(get()) }
}

internal val AccountsDataRepositoryModule = module {
    factory<AccountsRepository> { AccountsRepositoryImpl(get()) }
}

val AccountsDataModules = listOf(
    AccountsDataDatabaseModule,
    AccountsDataDaoModule,
    AccountsDataDataSourceModule,
    AccountsDataRepositoryModule
)