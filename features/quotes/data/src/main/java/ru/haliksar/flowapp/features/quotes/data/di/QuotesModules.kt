package ru.haliksar.flowapp.features.quotes.data.di

import androidx.paging.PagingData
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.features.quotes.data.api.QuotesApi
import ru.haliksar.flowapp.features.quotes.data.datasource.QuotesPagingDataSource
import ru.haliksar.flowapp.features.quotes.data.datasource.QuotesPagingDataSourceImpl
import ru.haliksar.flowapp.features.quotes.data.repository.QuotesRepositoryImpl
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.quotes.domain.repository.QuotesPagingRepository
import ru.haliksar.flowapp.libraries.network.createRetrofitService
import ru.haliksar.flowapp.libraries.network.di.FAKE

internal val QuotesApiModule = module {
    factory<QuotesApi> { createRetrofitService(get(named(FAKE))) }
}

internal val QuotesDataSourceModule = module {
    factory<QuotesPagingDataSource> { QuotesPagingDataSourceImpl(get()) }
}

@OptIn(KoinApiExtension::class)
internal val QuotesRepositoryModule = module {
    factory<QuotesPagingRepository<PagingData<QuotesEntity>>> { QuotesRepositoryImpl() }
}

val QuotesDataModules = listOf(
    QuotesApiModule,
    QuotesDataSourceModule,
    QuotesRepositoryModule
)