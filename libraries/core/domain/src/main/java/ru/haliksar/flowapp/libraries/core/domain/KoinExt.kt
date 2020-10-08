package ru.haliksar.flowapp.libraries.core.domain

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
fun <T, P> KoinComponent.useCaseByType(): Lazy<UseCase<T, P>> =
    inject<UseCase<T, P>>()

@KoinApiExtension
inline fun <reified U : UseCase<*, *>> KoinComponent.useCase(): Lazy<U> =
    inject<U>()