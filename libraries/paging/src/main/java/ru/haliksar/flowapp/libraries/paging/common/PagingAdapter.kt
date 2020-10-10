package ru.haliksar.flowapp.libraries.paging.common

import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

open class PagingAdapter(
    protected val nextPageCallback: () -> Unit,
    itemDiff: (old: Any, new: Any) -> Boolean,
    progressDelegate: AdapterDelegate<MutableList<Any>> = ProgressAdapterDelegate(),
    delegate: AdapterDelegate<MutableList<Any>>
) : AsyncListDifferDelegationAdapter<Any>(DiffItemCallback(itemDiff)) {

    var fullData = false

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(progressDelegate)
        delegatesManager.addDelegate(delegate)
    }

    open fun update(data: List<Any>, isPageProgress: Boolean) {
        items = mutableListOf<Any>().apply {
            addAll(data)
            if (isPageProgress) {
                add(ProgressItem)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any?>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (!fullData && position >= items.size - 10) {
            nextPageCallback.invoke()
        }
    }
}

