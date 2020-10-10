package ru.haliksar.flowapp.features.news.presentation.paging

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData

class NewsAdapter(
    private val list: MutableList<NewsUiData>,
    private val clickListener: (View, NewsUiData?) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(parent, clickListener)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun addAll(list: List<NewsUiData>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}