package ru.haliksar.flowapp.features.quotes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.haliksar.flowapp.features.quotes.data.datasource.QuotesPagingDataSource
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.quotes.domain.repository.QuotesPagingRepository

@KoinApiExtension
class QuotesRepositoryImpl : QuotesPagingRepository<PagingData<QuotesEntity>>, KoinComponent {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        private const val DEFAULT_PAGE_SIZE = 20

        fun getDefaultPageConfig(): PagingConfig =
            PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    override fun getQuotes(): Flow<PagingData<QuotesEntity>> =
        Pager(
            getDefaultPageConfig(),
            pagingSourceFactory = { get<QuotesPagingDataSource>() }
        ).flow
}