package ru.haliksar.flowapp.features.quotes.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowapp.features.quotes.data.di.QuotesDataModules
import ru.haliksar.flowapp.features.quotes.domain.di.QuotesDomainModules
import ru.haliksar.flowapp.features.quotes.presentation.QuotesViewModel

@OptIn(KoinApiExtension::class)
internal val viewModelModule = module { viewModel { QuotesViewModel() } }

val QuotesModules = listOf(viewModelModule) + QuotesDataModules + QuotesDomainModules