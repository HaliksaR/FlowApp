package ru.haliksar.flowapp.features.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.haliksar.flowapp.features.news.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.news.domain.repository.QuotesRepository
import ru.haliksar.flowapp.libraries.core.domain.UseCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

typealias QuotesUseCaseT = UseCase<Flow<NetworkResponse<out List<QuotesEntity>>>, Int>

class QuotesUseCase(private val repository: QuotesRepository) : QuotesUseCaseT {

    override operator fun invoke(param: Int): Flow<NetworkResponse<out List<QuotesEntity>>> =
        repository.getQuotes(param)
}