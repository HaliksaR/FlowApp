package ru.haliksar.flowapp.libraries.paging.notmutable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.paging_view.view.*
import ru.haliksar.flowapp.libraries.paging.R
import ru.haliksar.flowapp.libraries.paging.common.EmptyViewContract
import ru.haliksar.flowapp.libraries.paging.common.PagingAdapter
import ru.haliksar.flowapp.libraries.paging.redux.State

class PagingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var refreshCallback: (() -> Unit)? = null
        set(value) {
            field = value
        }

    var adapter: PagingAdapter? = null
        set(value) {
            field = value
            recyclerView.adapter = value
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
