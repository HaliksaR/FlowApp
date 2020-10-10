package ru.haliksar.flowapp.features.news.presentation.paging

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData

class NewsAdapterDelegate(
    private val clickListener: (View, NewsUiData?) -> Unit
) : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        NewsViewHolder(parent, clickListener)

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as NewsViewHolder).bind(items[position] as NewsUiData)
}