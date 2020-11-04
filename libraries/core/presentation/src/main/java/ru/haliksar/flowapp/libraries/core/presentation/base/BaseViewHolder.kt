package ru.haliksar.flowapp.libraries.core.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : Any>(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    protected lateinit var data: T

    open fun bind(data: T) {
        this.data = data
        bindContent()
        setListeners()
    }

    abstract fun bindContent()

    abstract fun setListeners()
}