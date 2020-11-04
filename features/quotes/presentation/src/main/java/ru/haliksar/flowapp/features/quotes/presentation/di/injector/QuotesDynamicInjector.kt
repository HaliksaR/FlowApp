package ru.haliksar.flowapp.features.quotes.presentation.di.injector

import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.quotes.presentation.di.QuotesModules
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector

@KoinApiExtension
class QuotesDynamicInjector : DynamicInjector(QuotesModules)