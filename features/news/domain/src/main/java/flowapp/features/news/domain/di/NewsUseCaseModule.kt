package flowapp.features.news.domain.di

import flowapp.features.news.domain.usecase.NewsUseCase
import flowapp.features.news.domain.usecase.NewsUseCaseT
import org.koin.dsl.module

val NewsUseCaseModule = module {
    factory<NewsUseCaseT> { NewsUseCase(get()) }
}