package ru.haliksar.flowapp.libraries.paging

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.paging_view.view.*

class PagingView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var refreshCallback: (() -> Unit)? = null

    private var adapter: PagingAdapter? = null

    init {
        inflate(context, R.layout.paging_view, this)
        swipeToRefresh.setOnRefreshListener { refreshCallback?.invoke() }
        emptyView.setOnClickListener { refreshCallback?.invoke() }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun init(
        adapter: PagingAdapter,
        refreshCallback: () -> Unit
    ) {
        this.refreshCallback = refreshCallback
        this.adapter = adapter
        recyclerView.adapter = adapter
    }

    @Suppress("UNCHECKED_CAST")
    fun render(state: Paging.State) {
        post {
            when (state) {
                is Paging.State.Empty -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.isVisible(false)
                    emptyView.isVisible(true)
                    swipeToRefresh.isVisible(true)
                }
                is Paging.State.EmptyProgress -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.isVisible(true)
                    emptyView.isVisible(false)
                    swipeToRefresh.isVisible(false)
                }
                is Paging.State.EmptyError -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.isVisible(false)
                    emptyView.isVisible(true)
                    swipeToRefresh.isVisible(true)
                }
                is Paging.State.Data<*> -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.isVisible(false)
                    adapter?.setData(state.data)
                    emptyView.isVisible(false)
                    swipeToRefresh.isVisible(true)
                }
                is Paging.State.Refresh<*> -> {
                    swipeToRefresh.isRefreshing = true
                    fullscreenProgressView.isVisible(false)
                    adapter?.setData(state.data)
                    emptyView.isVisible(false)
                    swipeToRefresh.isVisible(true)
                }
                is Paging.State.NewPageProgress<*> -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.isVisible(false)
                    adapter?.setData(state.data.toMutableList().also { it.add(ProgressItem) })
                    emptyView.isVisible(false)
                    swipeToRefresh.isVisible(true)
                }
                is Paging.State.FullData<*> -> {
                    swipeToRefresh.isRefreshing = false
                    fullscreenProgressView.isVisible(false)
                    adapter?.setData(state.data, true)
                    emptyView.isVisible(false)
                    swipeToRefresh.isVisible(true)
                }
            }
        }
    }

    private fun View.isVisible(visible: Boolean) {
        this.visibility = if (visible) View.VISIBLE else View.GONE
    }
}