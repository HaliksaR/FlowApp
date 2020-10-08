package ru.haliksar.flowapp.features.news.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.domain.di.NEWS_USECASE
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT
import ru.haliksar.flowapp.features.news.presentation.di.NEWS_MAPPER_UIDATA
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsMapperUiDataT
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.features.news.presentation.uistate.*
import ru.haliksar.flowapp.libraries.core.data.mapperUiData
import ru.haliksar.flowapp.libraries.core.domain.useCase
import ru.haliksar.flowapp.libraries.core.presentation.BaseViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse

@KoinApiExtension
@ExperimentalCoroutinesApi
class NewsViewModel : BaseViewModel<UiState>() {

    override val uiState = MutableStateFlow<UiState>(UiState.Nothing)

    private val useCase by useCase<NewsUseCaseT>(named(NEWS_USECASE))

    private val mapperUiData by mapperUiData<NewsMapperUiDataT>(named(NEWS_MAPPER_UIDATA))

    val newsFlow = MutableStateFlow<List<NewsUiData>>(emptyList())

    fun getNews() {
        useCase("").onEach { response ->
            when (response) {
                NetworkResponse.Loading -> uiState.loading()
                is NetworkResponse.Success -> {
                    newsFlow.value = response.data.map { mapperUiData.toUiData(it) }
                    uiState.success(response.data.map { mapperUiData.toUiData(it) })
                }
                is NetworkResponse.Error -> uiState.error(response.exception)
            }
        }.launchIn(viewModelScope)
    }

    fun navigate() {
        uiState.nothing()
    }
}