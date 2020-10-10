package ru.haliksar.flowapp.libraries.paging.redux

sealed class SideEffect {
    data class LoadPage(val currentPage: Int) : SideEffect()
    data class ErrorEvent(val error: Exception) : SideEffect()
}