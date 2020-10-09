package ru.haliksar.flowapp.libraries.core.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.haliksar.flowapp.libraries.core.presentation.ext.inflate

abstract class BaseViewHolder<T : Any>(parent: ViewGroup, layoutId: Int) :
    RecyclerView.ViewHolder(parent.inflate(layoutId)) {

    protected lateinit var data: T

    open fun bind(data: T) {
        this.data = data
        bindContent()
        setListeners()
    }

    abstract fun bindContent()

    abstract fun setListeners()
}