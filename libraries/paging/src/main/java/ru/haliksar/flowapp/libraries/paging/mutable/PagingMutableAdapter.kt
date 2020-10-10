package ru.haliksar.flowapp.libraries.paging.mutable

import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ru.haliksar.flowapp.libraries.paging.common.ItemTouchHelperContract
import ru.haliksar.flowapp.libraries.paging.common.PagingAdapter
import ru.haliksar.flowapp.libraries.paging.common.ProgressAdapterDelegate

class PagingMutableAdapter(
    nextPageCallback: () -> Unit,
    itemDiff: (old: Any, new: Any) -> Boolean,
    progressDelegate: AdapterDelegate<MutableList<Any>> = ProgressAdapterDelegate(),
    delegate: AdapterDelegate<MutableList<Any>>
) : PagingAdapter(nextPageCallback, itemDiff, progressDelegate, delegate),
    ItemTouchHelperContract {

    var itemMoved: ((fromPosition: Int, toPosition: Int) -> Unit)? = null
    var itemRemoved: ((position: Int) -> Unit)? = null

    var nextPageCaught = false
    var refreshStarted = false

    override fun update(data: List<Any>, isPageProgress: Boolean) {
        super.update(data, isPageProgress)
        nextPageCaught = false
        if (refreshStarted) {
            refreshStarted = false
            nextPageCallback()
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any?>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (!fullData && !nextPageCaught && position >= items.size - 10) {
            nextPageCaught = true
            nextPageCallback.invoke()
        }
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        itemMoved?.invoke(fromPosition, toPosition)
    }

    override fun onRowSelected(viewHolder: RecyclerView.ViewHolder) {}

    override fun onRowClear(viewHolder: RecyclerView.ViewHolder) {}

    override fun onRowSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemRemoved?.invoke(viewHolder.absoluteAdapterPosition)
    }
}