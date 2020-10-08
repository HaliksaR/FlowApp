@file:Suppress("EXPERIMENTAL_API_USAGE")

package ru.haliksar.flowapp.features.news.injector

import ru.haliksar.flowapp.features.news.data.di.NewsDataModules
import ru.haliksar.flowapp.features.news.domain.di.NewsUseCaseModule
import ru.haliksar.flowapp.features.news.presentation.di.NewsDataMappersModule
import ru.haliksar.flowapp.features.news.presentation.di.NewsViewModelModule

val NewsModules =
    listOf(NewsViewModelModule, NewsUseCaseModule, NewsDataMappersModule) + NewsDataModules