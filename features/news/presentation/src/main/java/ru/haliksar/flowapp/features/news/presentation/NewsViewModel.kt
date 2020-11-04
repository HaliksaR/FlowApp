package ru.haliksar.flowapp.features.news.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.domain.usecase.GetNewsUseCase
import ru.haliksar.flowapp.libraries.core.presentation.base.PagingMutableViewModel
import ru.haliksar.flowapp.libraries.network.wrappers.NetworkException
import ru.haliksar.flowapp.libraries.network.wrappers.toNetworkException
import ru.haliksar.flowapp.libraries.paging.mutable.ActionMutable

@KoinApiExtension
class NewsViewModel : PagingMutableViewModel<NewsEntity>(), KoinComponent {

    private val getNewsUseCase by inject<GetNewsUseCase>()

    private val errors = MutableStateFlow<NetworkException?>(null)

    fun observeErrors(action: (NetworkException?) -> Unit) {
        errors.onEach {
            action(it)
        }.launchIn(viewModelScope)
    }

    override fun loadNewPage(page: Int) {
        viewModelScope.launch {
            try {
                paging.proceed(
                    action = ActionMutable.NewPage(
                        pageNumber = page + 1,
                        items = getNewsUseCase(page)
                    )
                )
            } catch (throwable: Throwable) {
                paging.proceed(ActionMutable.PageError(throwable.toNetworkException()))
            }
        }
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