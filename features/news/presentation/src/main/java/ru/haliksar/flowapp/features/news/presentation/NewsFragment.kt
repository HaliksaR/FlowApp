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
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.presentation.paging.NewsAdapterDelegate
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.paging.PagingAdapter
import ru.haliksar.flowapp.navigation.GLOBAL_GRAPH
import ru.haliksar.flowapp.navigation.navigate

@KoinApiExtension
class NewsFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<NewsViewModel>()

    @ExperimentalCoroutinesApi
    private val adapter by lazy {
        PagingAdapter(
            nextPageCallback = {
                viewModel.loadMoreNews()
            },
            itemDiff = { oldItem, newItem ->
                if (oldItem is NewsUiData && newItem is NewsUiData)
                    oldItem.id == newItem.id
                else false
            },
            delegate = *arrayOf(
                NewsAdapterDelegate { view, item ->
                    view.profileUrl.movementMethod = LinkMovementMethod.getInstance()
                }
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.news_fragment, container, false)

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_sign_in -> {
                    navigate(
                        R.id.action_newsFragment_to_signInFragment,
                        hostId = get(named(GLOBAL_GRAPH))
                    )
                }
            }
            true
        }
        paging_view.init(adapter) {
            viewModel.refreshNews()
        }
        viewModel.uiStateObserve {
            paging_view.render(it)
        }
    }
}