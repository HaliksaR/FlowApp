package ru.haliksar.flowapp.features.quotes.domain.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.quotes.domain.usecase.QuotesUseCase
import ru.haliksar.flowapp.features.quotes.domain.usecase.QuotesUseCaseT

const val QUOTES_USECASE = "QUOTES_USECASE"

val QuotesUseCaseModule = module {
    factory<QuotesUseCaseT<Any, Any>>(named(QUOTES_USECASE)) { QuotesUseCase<Any, Any>(get()) }
}