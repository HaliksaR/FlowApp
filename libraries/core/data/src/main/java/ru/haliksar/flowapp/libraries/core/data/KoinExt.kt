package ru.haliksar.flowapp.libraries.core.data

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.Qualifier


@KoinApiExtension
fun <T, P> KoinComponent.mapperDtoByType(named: Qualifier? = null): Lazy<MapperDto<T, P>> =
    inject<MapperDto<T, P>>(named)

@KoinApiExtension
inline fun <reified U : MapperDto<*, *>> KoinComponent.mapperDto(named: Qualifier? = null): Lazy<U> =
    inject<U>(named)

@KoinApiExtension
fun <T, P> KoinComponent.mapperUiDataByType(named: Qualifier? = null): Lazy<MapperUiData<T, P>> =
    inject<MapperUiData<T, P>>(named)

@KoinApiExtension
inline fun <reified U : MapperUiData<*, *>> KoinComponent.mapperUiData(named: Qualifier? = null): Lazy<U> =
    inject<U>(named)