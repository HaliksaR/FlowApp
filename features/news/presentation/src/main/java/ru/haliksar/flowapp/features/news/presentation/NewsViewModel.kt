package ru.haliksar.flowapp.features.news.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.domain.di.NEWS_USECASE
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT
import ru.haliksar.flowapp.features.news.presentation.di.NEWS_MAPPER_UIDATA
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsMapperUiDataT
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.core.data.mapperUiData
import ru.haliksar.flowapp.libraries.core.domain.useCase
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.paging.Paging

@KoinApiExtension
@ExperimentalCoroutinesApi
class NewsViewModel : BaseViewModel<Paging.State>() {

    private val paging = Paging.Store<NewsUiData>()

    override var uiState = paging.flowRender

    private val useCase by useCase<NewsUseCaseT>(named(NEWS_USECASE))

    private val mapperUiData by mapperUiData<NewsMapperUiDataT>(named(NEWS_MAPPER_UIDATA))

    val errors = MutableStateFlow<NetworkException?>(null)

    init {
        setSideEffects()
        refreshNews()
    }

    private fun setSideEffects() {
        viewModelScope.launch {
            paging.sideEffects.consumeEach { effect ->
                when (effect) {
                    is Paging.SideEffect.LoadPage -> {
                        loadNews(effect.currentPage)
                    }
                    is Paging.SideEffect.ErrorEvent -> {
                        errors.value = effect.error as NetworkException
                    }
                }
            }
        }
    }

    private fun loadNews(page: Int) {
        useCase(page).onEach { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    val data = response.data.map { mapperUiData.toUiData(it) }
                    paging.proceed(Paging.Action.NewPage(page, data))
                }
                is NetworkResponse.Error -> {
                    paging.proceed(Paging.Action.PageError(response.exception))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadMoreNews() = paging.proceed(Paging.Action.LoadMore)

    fun refreshNews() = paging.proceed(Paging.Action.Refresh)
}