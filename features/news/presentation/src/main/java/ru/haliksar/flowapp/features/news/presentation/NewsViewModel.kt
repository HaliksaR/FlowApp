package ru.haliksar.flowapp.features.news.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.domain.di.NEWS_USECASE
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT
import ru.haliksar.flowapp.features.news.presentation.di.NEWS_MAPPER_UIDATA
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsMapperUiDataT
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.core.data.mapper.mapperUiData
import ru.haliksar.flowapp.libraries.core.domain.useCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.paging.mutable.ActionMutable
import ru.haliksar.flowapp.libraries.paging.mutable.PagingMutableViewModel

@FlowPreview
@ObsoleteCoroutinesApi
@KoinApiExtension
@ExperimentalCoroutinesApi
class NewsViewModel : PagingMutableViewModel<NewsUiData>(), KoinComponent {

    private val newsUseCase by useCase<NewsUseCaseT>(named(NEWS_USECASE))

    private val mapperNews by mapperUiData<NewsMapperUiDataT>(named(NEWS_MAPPER_UIDATA))

    private val errors = MutableStateFlow<NetworkException?>(null)

    fun observeErrors(action: (NetworkException?) -> Unit) {
        errors.onEach {
            action(it)
        }.launchIn(viewModelScope)
    }

    override fun loadNewPage(page: Int) {
        newsUseCase(page).onEach { news ->
            when (news) {
                is NetworkResponse.Success -> {
                    paging.proceed(
                        ActionMutable.NewPage(
                            pageNumber = page + 1,
                            items = news.data.map { mapperNews.toUiData(it) }
                        )
                    )
                }
                is NetworkResponse.Error -> {
                    paging.proceed(ActionMutable.PageError(news.exception))
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun errorEvent(error: Exception) {
        errors.value = error as? NetworkException
    }

    override fun onMove(from: Int, to: Int) {
        TODO("Not yet implemented")
    }

    override fun onRemove(index: Int) {
        TODO("Not yet implemented")
    }
}