package ru.haliksar.flowApp.features.user.accounts.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowApp.features.user.accounts.data.dao.AccountsDao
import ru.haliksar.flowApp.features.user.accounts.data.database.AccountDatabase
import ru.haliksar.flowApp.features.user.accounts.data.datasource.AccountsDataSource
import ru.haliksar.flowApp.features.user.accounts.data.datasource.AccountsDataSourceImpl
import ru.haliksar.flowApp.features.user.accounts.data.dto.TABLE_NAME
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserMapperDto
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserMapperDtoImpl
import ru.haliksar.flowApp.features.user.accounts.data.repository.AccountsRepositoryImpl
import ru.haliksar.flowApp.features.user.accounts.domain.repository.AccountsRepository


internal val AccountsDataMapperModule = module {
    factory<UserMapperDto> { UserMapperDtoImpl() }
}

internal val AccountsDataDatabaseModule = module {
    single<AccountDatabase> {
        Room.databaseBuilder(androidContext(), AccountDatabase::class.java, TABLE_NAME).build()
    }
}

internal val AccountsDataDaoModule = module {
    single<AccountsDao> { get<AccountDatabase>().accountsDao() }
}

@OptIn(KoinApiExtension::class)
internal val AccountsDataDataSourceModule = module {
    factory<AccountsDataSource> { AccountsDataSourceImpl(get()) }
}

internal val AccountsDataRepositoryModule = module {
    factory<AccountsRepository> { AccountsRepositoryImpl(get()) }
}

val AccountsDataModules = listOf(
    AccountsDataMapperModule,
    AccountsDataDatabaseModule,
    AccountsDataDaoModule,
    AccountsDataDataSourceModule,
    AccountsDataRepositoryModule
)