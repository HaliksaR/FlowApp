package ru.haliksar.flowapp.features.news.data.datasource

import androidx.paging.PagingSource
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.data.api.QuotesApi
import ru.haliksar.flowapp.features.news.data.di.QUOTES_MAPPER_DTO
import ru.haliksar.flowapp.features.news.data.dto.QuotesDto
import ru.haliksar.flowapp.features.news.data.repository.QuotesRepositoryImpl.Companion.DEFAULT_PAGE_INDEX
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto
import ru.haliksar.flowapp.libraries.core.data.mapperDto
import ru.haliksar.flowapp.libraries.network.pagingwrappers.safeCallPaging


@KoinApiExtension
class QuotesPagingDataSourceImpl(private val api: QuotesApi) : PagingSource<Int, QuotesEntity>(),
    KoinComponent {

    private val mapper by mapperDto<MapperDto<QuotesEntity, QuotesDto>>(named(QUOTES_MAPPER_DTO))

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuotesEntity> =
        safeCallPaging(
            page = params.key ?: DEFAULT_PAGE_INDEX,
            defaultPage = DEFAULT_PAGE_INDEX
        ) {
            api.getQuotes(
                page = params.key ?: DEFAULT_PAGE_INDEX,
                size = params.loadSize
            ).map {
                mapper.toEntity(it)
            }
        }
}