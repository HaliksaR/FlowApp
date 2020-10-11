package ru.haliksar.flowapp.features.quotes.domain.repository

import kotlinx.coroutines.flow.Flow

interface QuotesPagingRepository<T> {
    fun getQuotes(): Flow<T>
}