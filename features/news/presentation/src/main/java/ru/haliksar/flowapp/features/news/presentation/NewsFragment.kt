package ru.haliksar.flowapp.features.news.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.news.presentation.paging.NewsAdapterDelegate
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.core.presentation.ext.snack
import ru.haliksar.flowapp.libraries.paging.mutable.PagingMutableAdapter


@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
@KoinApiExtension
class NewsFragment : Fragment() {

    private val viewModel by viewModel<NewsViewModel>()

    private val adapter by lazy {
        PagingMutableAdapter(
            nextPageCallback = {
                viewModel.loadMore()
            },
            itemDiff = { oldItem, newItem ->
                if (oldItem is NewsUiData && newItem is NewsUiData) {
                    oldItem.id == newItem.id
                } else {
                    false
                }
            },
            delegate = NewsAdapterDelegate { view, _ ->
                view.profileUrl.movementMethod = LinkMovementMethod.getInstance()
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.news_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paging_view.adapter = adapter
        paging_view.refreshCallback = viewModel::refresh
        paging_view.itemMoved = viewModel::onMove
        paging_view.itemRemoved = viewModel::onRemove
        viewModel.pagingStateObserve {
            paging_view?.render(it)
        }
        viewModel.observeErrors {
            it?.let { snack(it.message.toString()) }
        }
    }
}