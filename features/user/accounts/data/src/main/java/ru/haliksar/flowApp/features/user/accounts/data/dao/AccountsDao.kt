package ru.haliksar.flowApp.features.user.accounts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.data.database.AccountDatabase.Companion.TABLE_NAME
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserDto

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDto)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("delete from $TABLE_NAME")
    suspend fun deleteAll()

    @Query("select * from $TABLE_NAME")
    fun getAll(): Flow<List<UserDto>>
}