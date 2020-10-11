package ru.haliksar.flowapp.features.quotes.data.di

import androidx.paging.PagingData
import androidx.paging.PagingSource
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.quotes.data.api.QuotesApi
import ru.haliksar.flowapp.features.quotes.data.datasource.QuotesPagingDataSourceImpl
import ru.haliksar.flowapp.features.quotes.data.dto.QuotesMapperDto
import ru.haliksar.flowapp.features.quotes.data.dto.QuotesMapperDtoT
import ru.haliksar.flowapp.features.quotes.data.repository.QuotesRepositoryImpl
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.quotes.domain.repository.QuotesPagingRepository
import ru.haliksar.flowapp.libraries.network.createRetrofitService
import ru.haliksar.flowapp.libraries.network.di.FAKE

internal val QuotesApiModule = module {
    factory<QuotesApi> { createRetrofitService(get(named(FAKE))) }
}

const val QUOTES_MAPPER_DTO = "QUOTES_MAPPER_DTO"

@OptIn(KoinApiExtension::class)
internal val QuotesDataMappersModule = module {
    factory<QuotesMapperDtoT>(named(QUOTES_MAPPER_DTO)) { QuotesMapperDto() }
}

@OptIn(KoinApiExtension::class)
internal val QuotesDataSourceModule = module {
    factory<PagingSource<Int, QuotesEntity>> { QuotesPagingDataSourceImpl(get()) }
}

@OptIn(KoinApiExtension::class)
internal val QuotesRepositoryModule = module {
    factory<QuotesPagingRepository<PagingData<QuotesEntity>>> { QuotesRepositoryImpl() }
}

val QuotesDataModules = listOf(
    QuotesApiModule,
    QuotesDataMappersModule,
    QuotesDataSourceModule,
    QuotesRepositoryModule
)