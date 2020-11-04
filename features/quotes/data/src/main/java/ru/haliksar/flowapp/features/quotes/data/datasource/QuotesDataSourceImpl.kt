package ru.haliksar.flowapp.features.quotes.data.datasource

import ru.haliksar.flowapp.features.quotes.data.api.QuotesApi
import ru.haliksar.flowapp.features.quotes.data.mapper.toEntity
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.libraries.network.pagingwrappers.safeCallPaging

class QuotesPagingDataSourceImpl(private val api: QuotesApi) : QuotesPagingDataSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuotesEntity> =
        safeCallPaging(
            page = params.key ?: DEFAULT_PAGE_INDEX,
            defaultPage = DEFAULT_PAGE_INDEX
        ) {
            api.getQuotes(
                page = params.key ?: DEFAULT_PAGE_INDEX,
                size = params.loadSize
            ).map { it.toEntity() }
        }
}