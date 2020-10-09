package ru.haliksar.flowapp.libraries.paging

import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PagingAdapter(
    private val nextPageCallback: () -> Unit,
    itemDiff: (old: Any, new: Any) -> Boolean,
    progressDelegate: AdapterDelegate<MutableList<Any>> = ProgressAdapterDelegate(),
    vararg delegate: AdapterDelegate<MutableList<Any>>
) : AsyncListDifferDelegationAdapter<Any>(DiffItemCallback(itemDiff)) {

    private var isFull: Boolean = false

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(progressDelegate)
        delegate.forEach { delegatesManager.addDelegate(it) }
    }

    fun setData(list: List<*>, isFull: Boolean = false) {
        this.isFull = isFull
        items = list.toList()
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any?>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (!isFull && position >= items.size - 10) nextPageCallback.invoke()
    }
}