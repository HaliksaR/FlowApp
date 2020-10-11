package ru.haliksar.flowapp.features.news.presentation.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.presentation.NewsViewModel
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsMapperUiData
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsMapperUiDataT

@ObsoleteCoroutinesApi
@FlowPreview
@OptIn(KoinApiExtension::class)
@ExperimentalCoroutinesApi
val NewsViewModelModule = module {
    viewModel { NewsViewModel() }
}

const val NEWS_MAPPER_UIDATA = "NEWS_MAPPER_UIDATA"

@OptIn(KoinApiExtension::class)
val NewsDataMappersModule = module {
    factory<NewsMapperUiDataT>(named(NEWS_MAPPER_UIDATA)) { NewsMapperUiData() }
}