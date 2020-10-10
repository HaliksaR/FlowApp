package ru.haliksar.flowapp.features.news.presentation.paging

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
abstract class PaginationListener<T : Any?>(
    private val layoutManager: LinearLayoutManager,
    private val state: MutableStateFlow<PagingState<T>>
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
        if (state.value is PagingState.Normal) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    companion object {
        const val PAGE_START = 1
        private const val PAGE_SIZE = 10
    }
}