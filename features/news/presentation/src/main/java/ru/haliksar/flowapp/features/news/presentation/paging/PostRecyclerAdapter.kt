package ru.haliksar.flowapp.features.news.presentation.paging

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData


class PostRecyclerAdapter(postItems: MutableList<NewsUiData?>) :
    RecyclerView.Adapter<BaseViewHolder<NewsUiData?>>() {

    private var isLoaderVisible = false
    private var isErrorVisible = false
    private var isFullVisible = false

    private val mNewsUiDatas: MutableList<NewsUiData?> = postItems

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<NewsUiData?> {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> NewsViewHolder(parent)
            VIEW_TYPE_LOADING -> ProgressHolder(parent)
            VIEW_TYPE_ERROR -> ErrorHolder(parent)
            VIEW_TYPE_FULL -> FullHolder(parent)
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<NewsUiData?>, position: Int) {
        holder.bind(mNewsUiDatas[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isErrorVisible && position == mNewsUiDatas.size - 1 -> {
                VIEW_TYPE_ERROR
            }
            isLoaderVisible && position == mNewsUiDatas.size - 1 -> {
                VIEW_TYPE_LOADING
            }
            isFullVisible && position == mNewsUiDatas.size - 1 -> {
                VIEW_TYPE_FULL
            }
            else -> {
                VIEW_TYPE_NORMAL
            }
        }
    }

    override fun getItemCount() = mNewsUiDatas.size

    fun addItems(postItems: List<NewsUiData>) {
        mNewsUiDatas.addAll(postItems)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        isErrorVisible = false
        isFullVisible = false
        mNewsUiDatas.add(null)
        notifyItemInserted(mNewsUiDatas.size - 1)
    }

    fun addError() {
        isErrorVisible = true
        isLoaderVisible = false
        isFullVisible = false
        mNewsUiDatas.add(null)
        notifyItemInserted(mNewsUiDatas.size - 1)
    }

    fun addFull() {
        isErrorVisible = false
        isLoaderVisible = false
        isFullVisible = true
        mNewsUiDatas.add(null)
        notifyItemInserted(mNewsUiDatas.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        isErrorVisible = false
        isFullVisible = false
        val position = mNewsUiDatas.size - 1
        if (getItem(position) != null) {
            mNewsUiDatas.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        mNewsUiDatas.clear()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): NewsUiData? = mNewsUiDatas[position]

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
        private const val VIEW_TYPE_ERROR = 2
        private const val VIEW_TYPE_FULL = 3
    }
}