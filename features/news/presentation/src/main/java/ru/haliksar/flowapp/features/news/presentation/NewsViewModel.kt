package ru.haliksar.flowapp.features.news.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.domain.di.NEWS_USECASE
import ru.haliksar.flowapp.features.news.domain.di.QUOTES_USECASE
import ru.haliksar.flowapp.features.news.domain.usecase.NewsUseCaseT
import ru.haliksar.flowapp.features.news.domain.usecase.QuotesUseCaseT
import ru.haliksar.flowapp.features.news.presentation.di.NEWS_MAPPER_UIDATA
import ru.haliksar.flowapp.features.news.presentation.di.QUOTES_MAPPER_UIDATA
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsMapperUiDataT
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.features.news.presentation.uidata.QuotesMapperUiDataT
import ru.haliksar.flowapp.libraries.core.data.mapperUiData
import ru.haliksar.flowapp.libraries.core.domain.useCase
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkResponse
import ru.haliksar.flowapp.libraries.paging.mutable.ActionMutable
import ru.haliksar.flowapp.libraries.paging.mutable.PagingMutableViewModel

@KoinApiExtension
@ExperimentalCoroutinesApi
class NewsViewModel : PagingMutableViewModel<NewsUiData>(), KoinComponent {

    private val newsUseCase by useCase<NewsUseCaseT>(named(NEWS_USECASE))
    private val quotesUseCase by useCase<QuotesUseCaseT>(named(QUOTES_USECASE))

    private val mapperNews by mapperUiData<NewsMapperUiDataT>(named(NEWS_MAPPER_UIDATA))
    private val mapperQuotes by mapperUiData<QuotesMapperUiDataT>(named(QUOTES_MAPPER_UIDATA))

    private val errors = MutableStateFlow<NetworkException?>(null)

    fun observeErrors(action: (NetworkException?) -> Unit) {
        errors.onEach {
            action(it)
        }.launchIn(viewModelScope)
    }

    override fun loadNewPage(page: Int) {
        newsUseCase(page).combine(quotesUseCase(page)) { news, quotes ->
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
/*            when (quotes) {
                is NetworkResponse.Success -> {
                    paging.proceed(
                        ActionMutable.NewPage(
                            pageNumber = page + 1,
                            items = quotes.data.map { mapperQuotes.toUiData(it) }
                        )
                    )
                }
                is NetworkResponse.Error -> {
                    paging.proceed(ActionMutable.PageError(quotes.exception))
                }
            }*/
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