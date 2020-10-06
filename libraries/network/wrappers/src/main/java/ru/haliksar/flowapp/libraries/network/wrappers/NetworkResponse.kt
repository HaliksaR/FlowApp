package ru.haliksar.flowapp.libraries.network.wrappers

import ru.haliksar.flowapp.libraries.core.data.MapperDto

sealed class NetworkResponse<T> {
    object Loading : NetworkResponse<Nothing>()
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error(val exception: NetworkException) : NetworkResponse<Nothing>()
}

fun <ENT, DTO> NetworkResponse<out DTO>.dataToEntity(
    mapperDto: MapperDto<ENT, DTO>
): NetworkResponse<out ENT> = when (this) {
    NetworkResponse.Loading -> NetworkResponse.Loading
    is NetworkResponse.Success -> NetworkResponse.Success(mapperDto.toEntity(data))
    is NetworkResponse.Error -> NetworkResponse.Error(exception)
}

fun <ENT, DTO> NetworkResponse<out ENT>.dataToDto(
    mapperDto: MapperDto<ENT, DTO>
): NetworkResponse<out DTO> = when (this) {
    NetworkResponse.Loading -> NetworkResponse.Loading
    is NetworkResponse.Success -> NetworkResponse.Success(mapperDto.toDto(data))
    is NetworkResponse.Error -> NetworkResponse.Error(exception)
}
