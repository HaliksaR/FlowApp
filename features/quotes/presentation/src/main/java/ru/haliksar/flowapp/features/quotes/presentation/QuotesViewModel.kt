package ru.haliksar.flowapp.features.quotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.quotes.domain.usecase.GetQuotesUseCase

@KoinApiExtension
class QuotesViewModel : ViewModel(), KoinComponent {

    private val getQuotesUseCase by inject<GetQuotesUseCase<PagingData<QuotesEntity>>>()

    fun fetchQuotes(): Flow<PagingData<QuotesEntity>> = getQuotesUseCase().cachedIn(viewModelScope)
}