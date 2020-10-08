package ru.haliksar.flowapp.libraries.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <ENT, DTO> Flow<DTO>.toEntity(
    mapperDto: MapperDto<ENT, DTO>
): Flow<ENT> = map { mapperDto.toEntity(it) }

fun <ENT, DTO> Flow<ENT>.toDto(
    mapperDto: MapperDto<ENT, DTO>
): Flow<DTO> = map { mapperDto.toDto(it) }

fun <ENT, DTO> Flow<List<DTO>?>.toListEntityOrNull(
    mapperDto: MapperDto<ENT, DTO>
): Flow<List<ENT>?> = map { list -> list?.map { mapperDto.toEntity(it) } }

fun <ENT, DTO> Flow<List<ENT>?>.toListDtoOrNull(
    mapperDto: MapperDto<ENT, DTO>
): Flow<List<DTO>?> = map { list -> list?.map { mapperDto.toDto(it) } }

fun <ENT, DTO> Flow<List<DTO>?>.toListEntityOrEmpty(
    mapperDto: MapperDto<ENT, DTO>
): Flow<List<ENT>> = map { list -> list?.map { mapperDto.toEntity(it) } ?: emptyList() }

fun <ENT, DTO> Flow<List<ENT>?>.toListDtoOrEmpty(
    mapperDto: MapperDto<ENT, DTO>
): Flow<List<DTO>> = map { list -> list?.map { mapperDto.toDto(it) } ?: emptyList() }

fun <ENT, DTO> Flow<List<DTO>>.toListEntity(
    mapperDto: MapperDto<ENT, DTO>
): Flow<List<ENT>> = map { list -> list.map { mapperDto.toEntity(it) } }

fun <ENT, DTO> Flow<List<ENT>>.toListDto(
    mapperDto: MapperDto<ENT, DTO>
): Flow<List<DTO>> = map { list -> list.map { mapperDto.toDto(it) } }

fun <ENT, UID> Flow<UID>.toEntity(
    mapperUiData: MapperUiData<ENT, UID>
): Flow<ENT> = map { mapperUiData.toEntity(it) }

fun <ENT, UID> Flow<ENT>.toUiData(
    mapperUiData: MapperUiData<ENT, UID>
): Flow<UID> = map { mapperUiData.toUiData(it) }

fun <ENT, DTO> Flow<List<DTO>>.toListEntity(
    mapperUiData: MapperUiData<ENT, DTO>
): Flow<List<ENT>> = map { list -> list.map { mapperUiData.toEntity(it) } }

fun <ENT, DTO> Flow<List<ENT>>.toListDto(
    mapperUiData: MapperUiData<ENT, DTO>
): Flow<List<DTO>> = map { list -> list.map { mapperUiData.toUiData(it) } }

fun <ENT, DTO> Flow<List<DTO>?>.toListEntityOrNull(
    mapperUiData: MapperUiData<ENT, DTO>
): Flow<List<ENT>?> = map { list -> list?.map { mapperUiData.toEntity(it) } }

fun <ENT, DTO> Flow<List<ENT>?>.toListDtoOrNull(
    mapperUiData: MapperUiData<ENT, DTO>
): Flow<List<DTO>?> = map { list -> list?.map { mapperUiData.toUiData(it) } }

fun <ENT, DTO> Flow<List<DTO>?>.toListEntityOrEmpty(
    mapperUiData: MapperUiData<ENT, DTO>
): Flow<List<ENT>> = map { list -> list?.map { mapperUiData.toEntity(it) } ?: emptyList() }

fun <ENT, DTO> Flow<List<ENT>?>.toListDtoOrEmpty(
    mapperUiData: MapperUiData<ENT, DTO>
): Flow<List<DTO>> = map { list -> list?.map { mapperUiData.toUiData(it) } ?: emptyList() }