package ru.haliksar.flowapp.libraries.paging.mutable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.paging_view.view.*
import ru.haliksar.flowapp.libraries.paging.R
import ru.haliksar.flowapp.libraries.paging.common.EmptyViewContract
import ru.haliksar.flowapp.libraries.paging.common.ItemTouchHelperCallback
import ru.haliksar.flowapp.libraries.paging.redux.State

class PagingViewMutable @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var itemMoved: ((fromPosition: Int, toPosition: Int) -> Unit)? = null
    var itemRemoved: ((position: Int) -> Unit)? = null
    var itemTouchHelper: ItemTouchHelper? = null

    var refreshCallback: (() -> Unit)? = null

    var adapter: PagingMutableAdapter? = null
        set(value) {
            field = value
            value?.itemMoved = itemMoved
            value?.itemRemoved = itemRemoved
            recyclerView.adapter = value
            linkItemTouchHelper()
        }

    var emptyView: EmptyViewContract? = null
        set(value) {
            field = value
            field?.setRefreshListener { refreshCallback?.let { it() } }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.paging_view, this, true)
        swipeToRefresh.setOnRefreshListener { refreshCallback?.invoke() }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun linkItemTouchHelper() {
        itemTouchHelper?.attachToRecyclerView(null)
        itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter
            )
        )
        itemTouchHelper?.attachToRecyclerView(recyclerView)
    }

    fun render(state: State) {
        post {
            when (state) {
                is State.Empty -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.visible(false)
                    adapter?.fullData = true
                    adapter?.update(emptyList(), false)
                    emptyView?.showEmptyData()
                    swipeToRefresh.visible(true)
                }
                is State.EmptyProgress -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.visible(true)
                    adapter?.fullData = false
                    adapter?.update(emptyList(), false)
                    emptyView?.hide()
                    swipeToRefresh.visible(false)
                }
                is State.EmptyError -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.visible(false)
                    adapter?.fullData = false
                    adapter?.update(emptyList(), false)
                    emptyView?.showEmptyError()
                    swipeToRefresh.visible(true)
                }
                is State.Data<*> -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.visible(false)
                    adapter?.fullData = false
                    adapter?.update(state.data, false)
                    emptyView?.hide()
                    swipeToRefresh.visible(true)
                }
                is State.Refresh<*> -> {
                    swipeToRefresh.isRefreshing = true
                    fullscreenProgressView.visible(false)
                    adapter?.fullData = false
                    adapter?.refreshStarted = true
                    adapter?.update(state.data, false)
                    emptyView?.hide()
                    swipeToRefresh.visible(true)
                }
                is State.NewPageProgress<*> -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.visible(false)
                    adapter?.fullData = false
                    adapter?.update(state.data, true)
                    emptyView?.hide()
                    swipeToRefresh.visible(true)
                }
                is State.FullData<*> -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.visible(false)
                    adapter?.fullData = true
                    adapter?.update(state.data, false)
                    emptyView?.hide()
                    swipeToRefresh.visible(true)
                }
            }
        }
    }

    private fun View.visible(visible: Boolean) {
        this.visibility = if (visible) View.VISIBLE else View.GONE
    }
}