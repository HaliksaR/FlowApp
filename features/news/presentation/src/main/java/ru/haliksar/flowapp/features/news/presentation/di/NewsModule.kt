package ru.haliksar.flowapp.features.news.presentation.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.presentation.NewsViewModel
import ru.haliksar.flowapp.features.news.presentation.uidata.*

@OptIn(KoinApiExtension::class)
@ExperimentalCoroutinesApi
val NewsViewModelModule = module {
    viewModel { NewsViewModel() }
}

const val NEWS_MAPPER_UIDATA = "NEWS_MAPPER_UIDATA"
const val AUTHOR_MAPPER_UIDATA = "AUTHOR_MAPPER_UIDATA"
const val URL_MAPPER_UIDATA = "URL_MAPPER_UIDATA"

@OptIn(KoinApiExtension::class)
val NewsDataMappersModule = module {
    factory<NewsMapperUiDataT>(named(NEWS_MAPPER_UIDATA)) { NewsMapperUiData() }
    factory<AuthorMapperUiDataT>(named(AUTHOR_MAPPER_UIDATA)) { AuthorMapperUiData() }
    factory<UrlMapperUiDataT>(named(URL_MAPPER_UIDATA)) { UrlMapperUiData() }
}