package ru.haliksar.flowapp.features.news.presentation.di.injector

import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.news.presentation.di.NewsModules
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector

@KoinApiExtension
class NewsDynamicInjector : DynamicInjector(NewsModules)