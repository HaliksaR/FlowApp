package ru.haliksar.flowapp.features.quotes.domain.di

import org.koin.dsl.module
import ru.haliksar.flowapp.features.quotes.domain.usecase.GetQuotesUseCase


internal val QuotesModuleUseCase = module {
    factory { GetQuotesUseCase<Any>(get()) }
}

val QuotesDomainModules = listOf(
    QuotesModuleUseCase
)