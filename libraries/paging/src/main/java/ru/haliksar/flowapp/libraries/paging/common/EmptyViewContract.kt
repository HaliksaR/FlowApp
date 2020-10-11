package ru.haliksar.flowapp.libraries.paging.common


interface EmptyViewContract {
    val emptyError: String?
    fun setRefreshListener(listener: () -> Unit)
    fun showEmptyData()
    fun setEmptyError(msg: String?)
    fun showEmptyError()
    fun hide()
}
