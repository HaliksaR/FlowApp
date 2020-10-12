package ru.haliksar.flowapp.features.news.shared.data.di

import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.shared.data.dto.AuthorMapperDto
import ru.haliksar.flowapp.features.news.shared.data.dto.AuthorMapperDtoT
import ru.haliksar.flowapp.features.news.shared.data.dto.UrlMapperDto
import ru.haliksar.flowapp.features.news.shared.data.dto.UrlMapperDtoT

const val AUTHOR_MAPPER_DTO = "AUTHOR_MAPPER_DTO"
const val URL_MAPPER_DTO = "URL_MAPPER_DTO"

@OptIn(KoinApiExtension::class)
internal val NewsSharedDataMappersModule = module {
    factory<AuthorMapperDtoT>(named(AUTHOR_MAPPER_DTO)) { AuthorMapperDto() }
    factory<UrlMapperDtoT>(named(URL_MAPPER_DTO)) { UrlMapperDto() }
}

val NewsSharedDataModules = listOf(
    NewsSharedDataMappersModule,
)