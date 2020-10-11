package ru.haliksar.flowapp.libraries.paging.mutable

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.haliksar.flowapp.libraries.paging.common.swap
import ru.haliksar.flowapp.libraries.paging.redux.SideEffect
import ru.haliksar.flowapp.libraries.paging.redux.State

sealed class ActionMutable {
    object Refresh : ActionMutable()
    object Restart : ActionMutable()
    object LoadMore : ActionMutable()
    data class NewPage<T : Any>(val pageNumber: Int, val items: List<T>) : ActionMutable()
    data class Update<T : Any>(val item: T, val index: Int) : ActionMutable()
    data class Move(val from: Int, val to: Int) : ActionMutable()
    data class Remove(val index: Int) : ActionMutable()
    data class Insert<T : Any>(val item: T) : ActionMutable()
    data class PageError(val error: Exception) : ActionMutable()
}

private fun <T : Any> reducer(
    action: ActionMutable,
    state: State,
    sideEffectListener: (SideEffect) -> Unit
): State = when (action) {
    is ActionMutable.Refresh -> {
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
    is ActionMutable.Restart -> {
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
    is ActionMutable.LoadMore -> {
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
    is ActionMutable.NewPage<*> -> {
        when {
            state is State.EmptyProgress && action.items.isEmpty() -> {
                State.Empty
            }
            state is State.EmptyProgress -> {
                State.Data(1, action.items)
            }
            state is State.Refresh<*> && action.items.isEmpty() -> {
                State.Empty
            }
            state is State.Refresh<*> -> {
                State.Data(
                    1,
                    action.items
                )
            }
            state is State.NewPageProgress<*> && action.items.isEmpty() -> {
                State.FullData(
                    state.pageCount,
                    state.data
                )
            }
            state is State.NewPageProgress<*> -> {
                State.Data(
                    state.pageCount + 1,
                    state.data + action.items
                )
            }
            else -> state
        }
    }
    is ActionMutable.Update<*> -> {
        when (state) {
            is State.Data<*> -> State.Data(
                state.pageCount,
                state.data.toMutableList().apply { set(action.index, action.item) }
            )
            is State.FullData<*> -> State.FullData(
                state.pageCount,
                state.data.toMutableList().apply { set(action.index, action.item) }
            )
            else -> state
        }
    }
    is ActionMutable.Move -> {
        when (state) {
            is State.Data<*> -> State.Data(
                state.pageCount,
                state.data.toMutableList().apply { swap(action.from, action.to) }
            )
            is State.FullData<*> -> State.FullData(
                state.pageCount,
                state.data.toMutableList().apply { swap(action.from, action.to) }
            )
            else -> state
        }
    }
    is ActionMutable.Remove -> {
        when {
            state is State.Data<*> && state.data.size > 1 -> {
                State.Data(
                    state.pageCount,
                    state.data.toMutableList().apply { removeAt(action.index) }
                )
            }
            state is State.FullData<*> && state.data.size > 1 ->
                State.FullData(
                    state.pageCount,
                    state.data.toMutableList().apply { removeAt(action.index) }
                )
            state is State.Data<*> || state is State.FullData<*> -> State.Empty
            else -> state
        }
    }
    is ActionMutable.Insert<*> -> {
        when (state) {
            is State.Empty -> State.FullData(
                1,
                listOf(action.item)
            )
            is State.Data<*> -> State.Data(
                state.pageCount,
                state.data + action.item
            )
            is State.FullData<*> -> State.FullData(
                state.pageCount,
                state.data + action.item
            )
            else -> state
        }
    }
    is ActionMutable.PageError -> {
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

@ExperimentalCoroutinesApi
class Store<T : Any>(scope: CoroutineScope) : CoroutineScope by scope {
    val render = MutableStateFlow<State>(State.Empty)
    val sideEffects = Channel<SideEffect>()

    fun proceed(action: ActionMutable) {
        val newState =
            reducer<T>(action, render.value) { sideEffect ->
                launch {
                    sideEffects.send(sideEffect)
                }
            }
        if (newState != render.value) {
            render.value = newState
        }
    }
}