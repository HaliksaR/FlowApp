package ru.haliksar.flowapp.features.quotes.data.datasource

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity

abstract class QuotesPagingDataSource : PagingSource<Int, QuotesEntity>() {
    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        private const val DEFAULT_PAGE_SIZE = 20

        fun getDefaultPageConfig(): PagingConfig =
            PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}