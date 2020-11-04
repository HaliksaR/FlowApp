package ru.haliksar.flowapp.libraries.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.libraries.paging.mutable.ActionMutable
import ru.haliksar.flowapp.libraries.paging.mutable.PagingMutableContract
import ru.haliksar.flowapp.libraries.paging.mutable.Store
import ru.haliksar.flowapp.libraries.paging.redux.SideEffect
import ru.haliksar.flowapp.libraries.paging.redux.State

@KoinApiExtension
abstract class PagingMutableViewModel<T : Any> : ViewModel(),
    PagingMutableContract<T> {

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
    abstract override fun onMove(from: Int, to: Int)
    abstract override fun onRemove(index: Int)

    override fun pagingStateObserve(action: (State) -> Unit) {
        pagingState.onEach {
            action(it)
        }.launchIn(viewModelScope)
    }

    override fun refresh() = paging.proceed(ActionMutable.Refresh)

    override fun loadMore() = paging.proceed(ActionMutable.LoadMore)
}