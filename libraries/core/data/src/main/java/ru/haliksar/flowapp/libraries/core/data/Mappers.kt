package ru.haliksar.flowapp.libraries.core.data

abstract class MapperDto<ENT, DTO> {
    abstract fun toDto(entity: ENT): DTO
    abstract fun toEntity(dto: DTO): ENT
}

abstract class MapperUiData<ENT, UID> {
    abstract fun toUiData(entity: ENT): UID
    abstract fun toEntity(uiData: UID): ENT
}