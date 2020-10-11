package ru.haliksar.flowapp.features.news.domain.repository

import kotlinx.coroutines.flow.Flow

interface QuotesPagingRepository<T> {
    fun getQuotes(): Flow<T>
}