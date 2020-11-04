package ru.haliksar.flowapp.libraries.paging.redux

sealed class State {
    object Empty : State()
    object EmptyProgress : State()
    data class EmptyError(val error: Throwable) : State()
    data class Data<T : Any>(val pageCount: Int, val data: List<T>) : State()
    data class Refresh<T : Any>(val pageCount: Int, val data: List<T>) : State()
    data class NewPageProgress<T : Any>(val pageCount: Int, val data: List<T>) : State()
    data class FullData<T : Any>(val pageCount: Int, val data: List<T>) : State()
}
