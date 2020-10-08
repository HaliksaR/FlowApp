package ru.haliksar.flowapp.features.news.domain.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCase
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT

const val NEWS_USECASE = "NEWS_USECASE"

val NewsUseCaseModule = module {
    factory<NewsUseCaseT>(named(NEWS_USECASE)) { NewsUseCase(get()) }
}