package ru.haliksar.flowapp.libraries.paging.common


internal fun <T> MutableList<T>.swap(fromPosition: Int, toPosition: Int) {
    val item = this[fromPosition]
    removeAt(fromPosition)
    add(toPosition, item)
}