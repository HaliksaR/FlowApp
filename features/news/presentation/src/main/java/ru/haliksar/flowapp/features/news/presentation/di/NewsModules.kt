package ru.haliksar.flowapp.features.news.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import ru.haliksar.flowapp.features.news.data.di.NewsDataModules
import ru.haliksar.flowapp.features.news.domain.di.NewsDomainModules
import ru.haliksar.flowapp.features.news.presentation.NewsViewModel

@OptIn(KoinApiExtension::class)
internal val viewModelModule = module { viewModel { NewsViewModel() } }

val NewsModules = listOf(viewModelModule) + NewsDataModules + NewsDomainModules