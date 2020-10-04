package ru.haliksar.flowapp.libraries.core.data

abstract class Mapper<ENT, DTO> {
    abstract fun toDto(entity: ENT): DTO
    abstract fun toEntity(dto: DTO): ENT
}