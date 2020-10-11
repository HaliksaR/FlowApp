package ru.haliksar.flowapp.libraries.paging.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffItemCallback(
    private val itemDiff: (old: Any, new: Any) -> Boolean
) : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem === newItem) return true
        return itemDiff.invoke(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: Any, newItem: Any) = Any()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any) = oldItem == newItem
}
