package ru.haliksar.flowapp.features.quotes.presentation.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.quotes.presentation.QuotesViewModel
import ru.haliksar.flowapp.features.quotes.presentation.uidata.QuotesMapperUiData
import ru.haliksar.flowapp.features.quotes.presentation.uidata.QuotesMapperUiDataT

@ObsoleteCoroutinesApi
@FlowPreview
@OptIn(KoinApiExtension::class)
@ExperimentalCoroutinesApi
val QuotesViewModelModule = module {
    viewModel { QuotesViewModel() }
}

const val QUOTES_MAPPER_UIDATA = "QUOTES_MAPPER_UIDATA"

@OptIn(KoinApiExtension::class)
val QuotesDataMappersModule = module {
    factory<QuotesMapperUiDataT>(named(QUOTES_MAPPER_UIDATA)) { QuotesMapperUiData() }
}