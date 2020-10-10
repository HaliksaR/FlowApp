package ru.haliksar.flowapp.features.news.data.datasource

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.data.api.QuotesApi
import ru.haliksar.flowapp.features.news.data.di.QUOTES_MAPPER_DTO
import ru.haliksar.flowapp.features.news.data.dto.QuotesDto
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.libraries.core.data.MapperDto
import ru.haliksar.flowapp.libraries.core.data.mapperDto
import ru.haliksar.flowapp.libraries.core.data.toListEntityOrEmpty


@KoinApiExtension
class QuotesDataSourceImpl(private val api: QuotesApi) : QuotesDataSource, KoinComponent {

    private val mapper by mapperDto<MapperDto<QuotesEntity, QuotesDto>>(named(QUOTES_MAPPER_DTO))

    override fun getQuotes(pageStartIndex: Int): Flow<List<QuotesEntity>> =
        api.getQuotes(pageStartIndex).toListEntityOrEmpty(mapper)
}