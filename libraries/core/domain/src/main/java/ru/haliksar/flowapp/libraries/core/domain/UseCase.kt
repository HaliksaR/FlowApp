package ru.haliksar.flowapp.libraries.core.domain

interface UseCase<T, P> {
    operator fun invoke(param: P): T
}