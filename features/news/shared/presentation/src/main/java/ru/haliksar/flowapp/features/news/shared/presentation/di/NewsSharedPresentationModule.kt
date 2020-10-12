package ru.haliksar.flowapp.features.news.shared.presentation.di

import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.shared.presentation.uidata.AuthorMapperUiData
import ru.haliksar.flowapp.features.news.shared.presentation.uidata.AuthorMapperUiDataT
import ru.haliksar.flowapp.features.news.shared.presentation.uidata.UrlMapperUiData
import ru.haliksar.flowapp.features.news.shared.presentation.uidata.UrlMapperUiDataT

const val AUTHOR_MAPPER_UIDATA = "AUTHOR_MAPPER_UIDATA"
const val URL_MAPPER_UIDATA = "URL_MAPPER_UIDATA"

@OptIn(KoinApiExtension::class)
val NewsSharedDataMappersModule = module {
    factory<AuthorMapperUiDataT>(named(AUTHOR_MAPPER_UIDATA)) { AuthorMapperUiData() }
    factory<UrlMapperUiDataT>(named(URL_MAPPER_UIDATA)) { UrlMapperUiData() }
}