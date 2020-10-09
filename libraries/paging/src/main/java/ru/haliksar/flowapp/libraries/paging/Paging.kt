package ru.haliksar.flowapp.libraries.paging

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
object Paging {

    sealed class State {
        object Empty : State()
        object EmptyProgress : State()
        data class EmptyError(val error: Exception) : State()
        data class Data<T>(val pageCount: Int, val data: List<T>) : State()
        data class Refresh<T>(val pageCount: Int, val data: List<T>) : State()
        data class NewPageProgress<T>(val pageCount: Int, val data: List<T>) : State()
        data class FullData<T>(val pageCount: Int, val data: List<T>) : State()
    }

    sealed class Action {
        object Refresh : Action()
        object Restart : Action()
        object LoadMore : Action()
        data class NewPage<T>(val pageNumber: Int, val items: List<T>) : Action()
        data class PageError(val error: Exception) : Action()
    }

    sealed class SideEffect {
        data class LoadPage(val currentPage: Int) : SideEffect()
        data class ErrorEvent(val error: Exception) : SideEffect()
    }

    private fun <T> reducer(
        action: Action,
        state: State,
        sideEffectListener: (SideEffect) -> Unit
    ): State =
        when (action) {
            is Action.Refresh -> {
                sideEffectListener(SideEffect.LoadPage(0))
                when (state) {
                    is State.Empty -> State.EmptyProgress
                    is State.EmptyError -> State.EmptyProgress
                    is State.Data<*> -> State.Refresh(state.pageCount, state.data)
                    is State.NewPageProgress<*> -> State.Refresh(state.pageCount, state.data)
                    is State.FullData<*> -> State.Refresh(state.pageCount, state.data)
                    else -> state
                }
            }
            is Action.Restart -> {
                sideEffectListener(SideEffect.LoadPage(0))
                when (state) {
                    is State.Empty -> State.EmptyProgress
                    is State.EmptyError -> State.EmptyProgress
                    is State.Data<*> -> State.EmptyProgress
                    is State.Refresh<*> -> State.EmptyProgress
                    is State.NewPageProgress<*> -> State.EmptyProgress
                    is State.FullData<*> -> State.EmptyProgress
                    else -> state
                }
            }
            is Action.LoadMore -> {
                when (state) {
                    is State.Data<*> -> {
                        sideEffectListener(SideEffect.LoadPage(state.pageCount + 1))
                        State.NewPageProgress(state.pageCount, state.data)
                    }
                    else -> state
                }
            }
            is Action.NewPage<*> -> {
                val items = action.items
                when (state) {
                    is State.EmptyProgress -> {
                        if (items.isEmpty()) {
                            State.Empty
                        } else {
                            State.Data(0, items)
                        }
                    }
                    is State.Refresh<*> -> {
                        if (items.isEmpty()) {
                            State.Empty
                        } else {
                            State.Data(0, items)
                        }
                    }
                    is State.NewPageProgress<*> -> {
                        if (items.isEmpty()) {
                            State.FullData(state.pageCount, state.data)
                        } else {
                            State.Data(state.pageCount + 1, state.data + items)
                        }
                    }
                    else -> state
                }
            }
            is Action.PageError -> {
                when (state) {
                    is State.EmptyProgress -> State.EmptyError(action.error)
                    is State.Refresh<*> -> {
                        sideEffectListener(SideEffect.ErrorEvent(action.error))
                        State.Data(state.pageCount, state.data)
                    }
                    is State.NewPageProgress<*> -> {
                        sideEffectListener(SideEffect.ErrorEvent(action.error))
                        State.Data(state.pageCount, state.data)
                    }
                    else -> state
                }
            }
        }

    class Store<T> : CoroutineScope by CoroutineScope(Dispatchers.Default) {
        @ExperimentalCoroutinesApi
        val flowRender = MutableStateFlow<State>(
            State.Empty
        )

        val sideEffects = Channel<SideEffect>()

        @ExperimentalCoroutinesApi
        fun proceed(action: Action) {
            val newState =
                reducer<T>(action, flowRender.value) { sideEffect ->
                    launch { sideEffects.send(sideEffect) }
                }
            if (newState != flowRender.value) {
                flowRender.value = newState
            }
        }
    }
}