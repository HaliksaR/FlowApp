package ru.haliksar.flowapp.libraries.core.data.di

import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.libraries.core.data.dto.AuthorMapperDto
import ru.haliksar.flowapp.libraries.core.data.dto.AuthorMapperDtoT
import ru.haliksar.flowapp.libraries.core.data.dto.UrlMapperDto
import ru.haliksar.flowapp.libraries.core.data.dto.UrlMapperDtoT

const val AUTHOR_MAPPER_DTO = "AUTHOR_MAPPER_DTO"
const val URL_MAPPER_DTO = "URL_MAPPER_DTO"

@OptIn(KoinApiExtension::class)
internal val CoreDataMappersModule = module {
    factory<AuthorMapperDtoT>(named(AUTHOR_MAPPER_DTO)) { AuthorMapperDto() }
    factory<UrlMapperDtoT>(named(URL_MAPPER_DTO)) { UrlMapperDto() }
}

val CoreDataModules = listOf(
    CoreDataMappersModule,
)