package ru.haliksar.flowApp.features.user.accounts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.haliksar.flowApp.features.user.accounts.data.dao.AccountsDao
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserDto

@Database(entities = [UserDto::class], version = 1)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountsDao(): AccountsDao
}