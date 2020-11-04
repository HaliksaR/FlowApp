package ru.haliksar.flowapp.features.quotes.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.quotes.domain.repository.QuotesPagingRepository

class GetQuotesUseCase<T : Any>(
    private val repository: QuotesPagingRepository<T>
) {

    operator fun invoke(): Flow<T> = repository.getQuotes()
}