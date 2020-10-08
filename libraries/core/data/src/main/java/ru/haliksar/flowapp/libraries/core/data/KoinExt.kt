package ru.haliksar.flowapp.libraries.core.data

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
fun <T, P> KoinComponent.mapperDtoByType(): Lazy<MapperDto<T, P>> =
    inject<MapperDto<T, P>>()

@KoinApiExtension
inline fun <reified U : MapperDto<*, *>> KoinComponent.mapperDto(): Lazy<U> =
    inject<U>()

@KoinApiExtension
fun <T, P> KoinComponent.mapperUiDataByType(): Lazy<MapperUiData<T, P>> =
    inject<MapperUiData<T, P>>()

@KoinApiExtension
inline fun <reified U : MapperUiData<*, *>> KoinComponent.mapperUiData(): Lazy<U> =
    inject<U>()