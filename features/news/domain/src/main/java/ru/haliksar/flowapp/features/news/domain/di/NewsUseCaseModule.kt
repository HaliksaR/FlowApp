package ru.haliksar.flowapp.features.news.domain.di

import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.domain.usecase.GetNewsUseCase

internal val NewsUseCaseModule = module {
    factory { GetNewsUseCase(get()) }
}

val NewsDomainModules = listOf(
    NewsUseCaseModule
)