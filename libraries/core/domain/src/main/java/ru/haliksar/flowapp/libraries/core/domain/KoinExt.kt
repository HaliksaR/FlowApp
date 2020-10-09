package ru.haliksar.flowapp.libraries.core.domain

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.Qualifier


@KoinApiExtension
fun <T, P> KoinComponent.useCaseByType(named: Qualifier? = null): Lazy<UseCase<T, P>> =
    inject<UseCase<T, P>>(named)

@KoinApiExtension
inline fun <reified U : UseCase<*, *>> KoinComponent.useCase(named: Qualifier? = null): Lazy<U> =
    inject<U>(named)