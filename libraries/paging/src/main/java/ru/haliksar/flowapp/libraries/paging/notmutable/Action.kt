package ru.haliksar.flowapp.libraries.paging.notmutable

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.haliksar.flowapp.libraries.paging.redux.SideEffect
import ru.haliksar.flowapp.libraries.paging.redux.State

sealed class Action {
    object Refresh : Action()
    object Restart : Action()
    object LoadMore : Action()
    data class NewPage<T : Any>(val pageNumber: Int, val items: List<T>) : Action()
    data class PageError(val error: Exception) : Action()
}

@ExperimentalCoroutinesApi
class Store<T : Any>(scope: CoroutineScope) : CoroutineScope by scope {
    val render = MutableStateFlow<State>(State.Empty)
    val sideEffects = Channel<SideEffect>()

    fun proceed(action: Action) {
        val newState =
            reducer<T>(action, render.value) { sideEffect ->
                launch { sideEffects.send(sideEffect) }
            }
        if (newState != render.value) {
            render.value = newState
        }
    }
}

private fun <T : Any> reducer(
    action: Action,
    state: State,
    sideEffectListener: (SideEffect) -> Unit
): State =
    when (action) {
        is Action.Refresh -> {
            sideEffectListener(
                SideEffect.LoadPage(
                    1
                )
            )
            when (state) {
                is State.Empty -> State.EmptyProgress
                is State.EmptyError -> State.EmptyProgress
                is State.Data<*> -> State.Refresh(
                    state.pageCount,
                    state.data
                )
                is State.NewPageProgress<*> -> State.Refresh(
                    state.pageCount,
                    state.data
                )
                is State.FullData<*> -> State.Refresh(
                    state.pageCount,
                    state.data
                )
                else -> state
            }
        }
        is Action.Restart -> {
            sideEffectListener(
                SideEffect.LoadPage(
                    1
                )
            )
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
            if (state is State.Data<*>) {
                sideEffectListener(
                    SideEffect.LoadPage(
                        state.pageCount + 1
                    )
                )
                State.NewPageProgress(
                    state.pageCount,
                    state.data
                )
            } else {
                state
            }
        }
        is Action.NewPage<*> -> {
            when (state) {
                is State.EmptyProgress -> {
                    if (action.items.isEmpty()) {
                        State.Empty
                    } else {
                        State.Data(
                            1,
                            action.items
                        )
                    }
                }
                is State.Refresh<*> -> {
                    if (action.items.isEmpty()) {
                        State.Empty
                    } else {
                        State.Data(
                            1,
                            action.items
                        )
                    }
                }
                is State.NewPageProgress<*> -> {
                    if (action.items.isEmpty()) {
                        State.FullData(
                            state.pageCount,
                            state.data
                        )
                    } else {
                        State.Data(
                            state.pageCount + 1,
                            state.data + action.items
                        )
                    }
                }
                else -> state
            }
        }
        is Action.PageError -> {
            when (state) {
                is State.EmptyProgress -> State.EmptyError(
                    action.error
                )
                is State.Refresh<*> -> {
                    sideEffectListener(
                        SideEffect.ErrorEvent(
                            action.error
                        )
                    )
                    State.Data(
                        state.pageCount,
                        state.data
                    )
                }
                is State.NewPageProgress<*> -> {
                    sideEffectListener(
                        SideEffect.ErrorEvent(
                            action.error
                        )
                    )
                    State.Data(
                        state.pageCount,
                        state.data
                    )
                }
                else -> state
            }
        }
    }