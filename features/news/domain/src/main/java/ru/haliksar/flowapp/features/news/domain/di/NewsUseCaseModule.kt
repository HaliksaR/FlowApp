package ru.haliksar.flowapp.features.news.domain.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCase
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT
import ru.haliksar.flowapp.features.news.domain.usecase.QuotesUseCase
import ru.haliksar.flowapp.features.news.domain.usecase.QuotesUseCaseT

const val NEWS_USECASE = "NEWS_USECASE"
const val QUOTES_USECASE = "QUOTES_USECASE"

val NewsUseCaseModule = module {
    factory<NewsUseCaseT>(named(NEWS_USECASE)) { NewsUseCase(get()) }
    factory<QuotesUseCaseT>(named(QUOTES_USECASE)) { QuotesUseCase(get()) }
}