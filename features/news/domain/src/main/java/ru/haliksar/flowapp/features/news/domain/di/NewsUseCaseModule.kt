package ru.haliksar.flowapp.features.news.domain.di

import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCase
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT

val NewsUseCaseModule = module {
    factory<NewsUseCaseT> { NewsUseCase(get()) }
}