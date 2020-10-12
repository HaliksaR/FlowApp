package ru.haliksar.flowapp.features.news.shared.injector

import ru.haliksar.flowapp.features.news.shared.data.di.NewsSharedDataModules
import ru.haliksar.flowapp.features.news.shared.presentation.di.NewsSharedDataMappersModule

val NewsSharedModules =
    listOf(
        NewsSharedDataMappersModule
    ) + NewsSharedDataModules