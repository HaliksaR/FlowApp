package ru.haliksar.flowapp.libraries.core.presentation.di

import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.libraries.core.presentation.uidata.AuthorMapperUiData
import ru.haliksar.flowapp.libraries.core.presentation.uidata.AuthorMapperUiDataT
import ru.haliksar.flowapp.libraries.core.presentation.uidata.UrlMapperUiData
import ru.haliksar.flowapp.libraries.core.presentation.uidata.UrlMapperUiDataT

const val AUTHOR_MAPPER_UIDATA = "AUTHOR_MAPPER_UIDATA"
const val URL_MAPPER_UIDATA = "URL_MAPPER_UIDATA"

@OptIn(KoinApiExtension::class)
val CoreDataMappersModule = module {
    factory<AuthorMapperUiDataT>(named(AUTHOR_MAPPER_UIDATA)) { AuthorMapperUiData() }
    factory<UrlMapperUiDataT>(named(URL_MAPPER_UIDATA)) { UrlMapperUiData() }
}