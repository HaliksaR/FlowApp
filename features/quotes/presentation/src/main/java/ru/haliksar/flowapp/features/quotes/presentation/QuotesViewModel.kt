package ru.haliksar.flowapp.features.quotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.quotes.domain.di.QUOTES_USECASE
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.quotes.domain.usecase.QuotesUseCaseT
import ru.haliksar.flowapp.features.quotes.presentation.di.QUOTES_MAPPER_UIDATA
import ru.haliksar.flowapp.features.quotes.presentation.uidata.QuotesMapperUiDataT
import ru.haliksar.flowapp.features.quotes.presentation.uidata.QuotesUiData
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperUiData
import ru.haliksar.flowapp.libraries.core.domain.useCase

@FlowPreview
@ObsoleteCoroutinesApi
@KoinApiExtension
@ExperimentalCoroutinesApi
class QuotesViewModel : ViewModel(), KoinComponent {

    private val quotesUseCase
            by useCase<QuotesUseCaseT<PagingData<QuotesEntity>, Unit>>(named(QUOTES_USECASE))

    private val mapperQuotes
            by mapperUiData<QuotesMapperUiDataT>(named(QUOTES_MAPPER_UIDATA))

    fun fetchQuotes(): Flow<PagingData<QuotesUiData>> =
        quotesUseCase(Unit).map {
            it.map {
                mapperQuotes.toUiData(it)
            }
        }.cachedIn(viewModelScope)
}