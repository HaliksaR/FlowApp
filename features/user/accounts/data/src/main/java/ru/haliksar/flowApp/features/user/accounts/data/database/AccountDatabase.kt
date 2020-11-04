package ru.haliksar.flowApp.features.user.accounts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.haliksar.flowApp.features.user.accounts.data.dao.AccountsDao
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserDto

@Database(entities = [UserDto::class], version = 1)
abstract class AccountDatabase : RoomDatabase() {
    internal companion object {
        const val NAME = "AccountDatabase"
        const val TABLE_NAME = "accounts"
    }

    abstract fun accountsDao(): AccountsDao
}