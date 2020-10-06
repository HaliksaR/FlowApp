package ru.haliksar.flowapp.libraries.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <ENT, DTO> Flow<DTO>.toEntity(
    mapperDto: MapperDto<ENT, DTO>
): Flow<ENT> = map { mapperDto.toEntity(it) }

fun <ENT, DTO> Flow<ENT>.toDto(
    mapperDto: MapperDto<ENT, DTO>
): Flow<DTO> = map { mapperDto.toDto(it) }


fun <ENT, UID> Flow<UID>.toEntity(
    mapperUiData: MapperUiData<ENT, UID>
): Flow<ENT> = map { mapperUiData.toEntity(it) }

fun <ENT, UID> Flow<ENT>.toUiData(
    mapperUiData: MapperUiData<ENT, UID>
): Flow<UID> = map { mapperUiData.toUiData(it) }