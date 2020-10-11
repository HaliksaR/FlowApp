package ru.haliksar.flowapp.features.news.data.repository

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.data.datasource.QuotesDataSource
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.news.domain.repository.QuotesRepository
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.network.wrappers.safeCallFlow

class QuotesRepositoryImpl(private val dataSource: QuotesDataSource) : QuotesRepository {

    override fun getQuotes(pageStartIndex: Int): Flow<NetworkResponse<out List<QuotesEntity>>> =
        safeCallFlow { dataSource.getQuotes(pageStartIndex) }
}