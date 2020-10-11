package ru.haliksar.flowapp.features.news.data.di

import androidx.paging.PagingData
import androidx.paging.PagingSource
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.data.api.NewsApi
import ru.haliksar.flowapp.features.news.data.api.QuotesApi
import ru.haliksar.flowapp.features.news.data.datasource.NewsDataSource
import ru.haliksar.flowapp.features.news.data.datasource.NewsDataSourceImpl
import ru.haliksar.flowapp.features.news.data.datasource.QuotesPagingDataSourceImpl
import ru.haliksar.flowapp.features.news.data.dto.*
import ru.haliksar.flowapp.features.news.data.repository.NewsRepositoryImpl
import ru.haliksar.flowapp.features.news.data.repository.QuotesRepositoryImpl
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.news.domain.repository.NewsRepository
import ru.haliksar.flowapp.features.news.domain.repository.QuotesPagingRepository
import ru.haliksar.flowapp.libraries.network.createRetrofitService
import ru.haliksar.flowapp.libraries.network.di.FAKE

internal val NewsApiModule = module {
    factory<NewsApi> { createRetrofitService(get(named(FAKE))) }
    factory<QuotesApi> { createRetrofitService(get(named(FAKE))) }
}

const val NEWS_MAPPER_DTO = "NEWS_MAPPER_DTO"
const val AUTHOR_MAPPER_DTO = "AUTHOR_MAPPER_DTO"
const val URL_MAPPER_DTO = "URL_MAPPER_DTO"
const val QUOTES_MAPPER_DTO = "QUOTES_MAPPER_DTO"

@OptIn(KoinApiExtension::class)
internal val NewsDataMappersModule = module {
    factory<NewsMapperDtoT>(named(NEWS_MAPPER_DTO)) { NewsMapperDto() }
    factory<AuthorMapperDtoT>(named(AUTHOR_MAPPER_DTO)) { AuthorMapperDto() }
    factory<UrlMapperDtoT>(named(URL_MAPPER_DTO)) { UrlMapperDto() }
    factory<QuotesMapperDtoT>(named(QUOTES_MAPPER_DTO)) { QuotesMapperDto() }
}

@OptIn(KoinApiExtension::class)
internal val NewsDataSourceModule = module {
    factory<NewsDataSource> { NewsDataSourceImpl(get()) }
    factory<PagingSource<Int, QuotesEntity>> { QuotesPagingDataSourceImpl(get()) }
}

@OptIn(KoinApiExtension::class)
internal val NewsRepositoryModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
    factory<QuotesPagingRepository<PagingData<QuotesEntity>>> { QuotesRepositoryImpl() }
}

val NewsDataModules = listOf(
    NewsApiModule,
    NewsDataMappersModule,
    NewsDataSourceModule,
    NewsRepositoryModule
)