@file:Suppress("EXPERIMENTAL_API_USAGE")

package ru.haliksar.flowapp.features.quotes.injector

import ru.haliksar.flowapp.features.quotes.data.di.QuotesDataModules
import ru.haliksar.flowapp.features.quotes.domain.di.QuotesUseCaseModule
import ru.haliksar.flowapp.features.quotes.presentation.di.QuotesDataMappersModule
import ru.haliksar.flowapp.features.quotes.presentation.di.QuotesViewModelModule


val QuotesModules =
    listOf(
        QuotesViewModelModule,
        QuotesUseCaseModule,
        QuotesDataMappersModule
    ) + QuotesDataModules