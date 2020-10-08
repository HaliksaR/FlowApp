package ru.haliksar.flowapp.features.news.data.di

import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.news.data.datasource.NewsDataSource
import ru.haliksar.flowapp.features.news.data.datasource.NewsDataSourceImpl
import ru.haliksar.flowapp.features.news.data.dto.*
import ru.haliksar.flowapp.features.news.data.repository.NewsRepositoryImpl
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository
import ru.haliksar.flowapp.libraries.network.createRetrofitService
import ru.haliksar.flowapp.libraries.network.di.FAKE

internal val NewsApiModule = module {
    factory<NewsApi> { createRetrofitService(get(named(FAKE))) }
}

@OptIn(KoinApiExtension::class)
internal val NewsDataMappersModule = module {
    factory<NewsMapperDtoT> { NewsMapperDto() }
    factory<AuthorMapperDtoT> { AuthorMapperDto() }
    factory<UrlMapperDtoT> { UrlMapperDto() }
}

@OptIn(KoinApiExtension::class)
internal val NewsDataSourceModule = module {
    factory<NewsDataSource> { NewsDataSourceImpl(get()) }
}

internal val NewsRepositoryModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
}

val NewsDataModules = listOf(
    NewsApiModule,
    NewsDataMappersModule,
    NewsDataSourceModule,
    NewsRepositoryModule
)