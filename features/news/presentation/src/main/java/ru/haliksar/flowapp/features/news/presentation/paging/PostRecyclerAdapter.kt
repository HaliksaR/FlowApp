package ru.haliksar.flowapp.features.news.presentation.paging

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

sealed class PagingState<T : Any?> {
    class Normal<T> : PagingState<T>()
    class Loading<T> : PagingState<T>()
    class Error<T> : PagingState<T>()
    class ClearData<T> : PagingState<T>()
    class FullData<T> : PagingState<T>()
    data class AddData<T>(val data: List<T>) : PagingState<T>()
}

@ExperimentalCoroutinesApi
class PostRecyclerAdapter<T : Any?>(
    private val data: MutableList<T?>,
    owner: LifecycleOwner
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    val state = MutableStateFlow<PagingState<T>>(
        PagingState.Normal<T>()
    )

    private companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_NORMAL = 1
        const val VIEW_TYPE_ERROR = 2
        const val VIEW_TYPE_FULL = 3
    }

    init {
        state.onEach {
            Log.d("state", it::class.simpleName.toString())
            when (it) {
                is PagingState.Normal -> {
                    if (data.lastOrNull() != null) {
                        data.removeAt(data.size - 1)
                        notifyItemRemoved(data.size - 1)
                    }
                }
                is PagingState.Loading -> {
                    data.add(null)
                    notifyItemInserted(data.size - 1)
                }
                is PagingState.Error -> {
                    data.add(null)
                    notifyItemInserted(data.size - 1)
                }
                is PagingState.FullData -> {
                    data.add(null)
                    notifyItemInserted(data.size - 1)
                }
                is PagingState.AddData -> {
                    data.addAll(it.data)
                    notifyDataSetChanged()
                    state.value = PagingState.Normal()
                }
                is PagingState.ClearData -> {
                    data.clear()
                    notifyDataSetChanged()
                }
            }
        }.launchIn(owner.lifecycle.coroutineScope)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<T> {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> NewsViewHolder<T>(parent)
            VIEW_TYPE_LOADING -> ProgressHolder<T>(parent)
            VIEW_TYPE_ERROR -> ErrorHolder<T>(parent)
            VIEW_TYPE_FULL -> FullHolder<T>(parent)
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemViewType(position: Int): Int =
        if (position == data.size - 1) {
            when (state.value) {
                is PagingState.Loading -> VIEW_TYPE_LOADING
                is PagingState.Error -> VIEW_TYPE_ERROR
                is PagingState.FullData -> VIEW_TYPE_FULL
                is PagingState.Normal,
                is PagingState.ClearData,
                is PagingState.AddData -> VIEW_TYPE_NORMAL
            }
        } else {
            VIEW_TYPE_NORMAL
        }

    override fun getItemCount() = data.size
}