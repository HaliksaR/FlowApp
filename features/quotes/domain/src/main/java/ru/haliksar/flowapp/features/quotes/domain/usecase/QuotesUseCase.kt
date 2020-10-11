package ru.haliksar.flowapp.features.quotes.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.quotes.domain.repository.QuotesPagingRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase

typealias QuotesUseCaseT<T, P> = UseCase<Flow<T>, P>

class QuotesUseCase<T : Any, P : Any>(
    private val repository: QuotesPagingRepository<T>
) : QuotesUseCaseT<T, P> {

    override operator fun invoke(param: P): Flow<T> =
        repository.getQuotes()
}