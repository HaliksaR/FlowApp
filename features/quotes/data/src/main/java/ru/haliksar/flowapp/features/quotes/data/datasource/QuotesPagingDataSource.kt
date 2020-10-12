package ru.haliksar.flowapp.features.quotes.data.datasource

import androidx.paging.PagingSource
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity

abstract class QuotesPagingDataSource : PagingSource<Int, QuotesEntity>()