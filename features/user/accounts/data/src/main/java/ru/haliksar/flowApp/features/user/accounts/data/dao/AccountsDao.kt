package ru.haliksar.flowApp.features.user.accounts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowApp.features.user.accounts.data.dto.UserDto

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserDto)

    @Query("DELETE FROM accounts WHERE id = :id")
    fun delete(id: Int)

    @Query("delete from accounts")
    fun deleteAll()

    @Query("select * from accounts")
    fun getAll(): Flow<List<UserDto>>
}