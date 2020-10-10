package ru.haliksar.flowapp.libraries.paging.notmutable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.haliksar.flowapp.libraries.paging.redux.SideEffect
import ru.haliksar.flowapp.libraries.paging.redux.State

@ExperimentalCoroutinesApi
abstract class PagingViewModel<T : Any> : ViewModel(), PagingContract<T> {

    final override val paging: Store<T> = Store(viewModelScope)
    override val pagingState: MutableStateFlow<State> = paging.render

    init {
        observeSideEffects()
    }

    final override fun observeSideEffects() {
        viewModelScope.launch {
            paging.sideEffects.consumeEach { effect ->
                when (effect) {
                    is SideEffect.LoadPage -> loadNewPage(effect.currentPage)
                    is SideEffect.ErrorEvent -> errorEvent(effect.error)
                }
            }
        }
    }

    abstract override fun loadNewPage(page: Int)
    abstract override fun errorEvent(error: Exception)

    override fun pagingStateObserve(action: (State) -> Unit) {
        pagingState.onEach {
            action(it)
        }.launchIn(viewModelScope)
    }

    override fun refresh() = paging.proceed(Action.Refresh)

    override fun loadMore() = paging.proceed(Action.LoadMore)
}