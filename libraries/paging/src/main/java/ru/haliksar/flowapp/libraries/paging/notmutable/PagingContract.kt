package ru.haliksar.flowapp.libraries.paging.notmutable

import kotlinx.coroutines.flow.MutableStateFlow
import ru.haliksar.flowapp.libraries.paging.redux.State

interface PagingContract<T : Any> {
    val paging: Store<T>
    val pagingState: MutableStateFlow<State>

    fun loadNewPage(page: Int)
    fun refresh()
    fun loadMore()
    fun observeSideEffects()
    fun pagingStateObserve(action: (State) -> Unit)
    fun errorEvent(error: Exception)
}