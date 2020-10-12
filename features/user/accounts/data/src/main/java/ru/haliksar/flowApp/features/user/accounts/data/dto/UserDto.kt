package ru.haliksar.flowApp.features.user.accounts.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.haliksar.flowApp.features.user.accounts.domain.entity.UserEntity
import ru.haliksar.flowapp.libraries.core.data.mapper.MapperDto

internal const val TABLE_NAME = "accounts"

@Entity(tableName = TABLE_NAME)
data class UserDto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "login") val login: String
) {
    override fun toString(): String =
        "id: $name id: $name surname: $surname age: $age login: $login"
}

abstract class UserMapperDto : MapperDto<UserEntity, UserDto>()

class UserMapperDtoImpl : UserMapperDto() {

    override fun toDto(entity: UserEntity): UserDto =
        UserDto(
            entity.id,
            entity.name,
            entity.surname,
            entity.age,
            entity.login
        )

    override fun toEntity(dto: UserDto): UserEntity =
        UserEntity(
            dto.id,
            dto.name,
            dto.surname,
            dto.age,
            dto.login
        )
}