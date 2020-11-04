package ru.haliksar.flowApp.features.user.accounts.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.haliksar.flowApp.features.user.accounts.data.database.AccountDatabase.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class UserDto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "login") val login: String
) {
    override fun toString(): String =
        "id: $id name: $name surname: $surname age: $age login: $login"
}