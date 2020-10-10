package ru.haliksar.flowapp.features.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.presentation.paging.PaginationListener
import ru.haliksar.flowapp.features.news.presentation.paging.PaginationListener.Companion.PAGE_START
import ru.haliksar.flowapp.features.news.presentation.paging.PagingState
import ru.haliksar.flowapp.features.news.presentation.paging.PostRecyclerAdapter
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.paging.Paging
import ru.haliksar.flowapp.navigation.GLOBAL_GRAPH
import ru.haliksar.flowapp.navigation.navigate


@ExperimentalCoroutinesApi
@KoinApiExtension
class NewsFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<NewsViewModel>()

/*    @ExperimentalCoroutinesApi
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
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.news_fragment, container, false)

    private var adapter = PostRecyclerAdapter<NewsUiData?>(ArrayList(), this)
    private var currentPage: Int = PAGE_START
    private var itemCount = 0

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
        swipeRefresh.setOnRefreshListener {
            itemCount = 0
            currentPage = PAGE_START
            adapter.state.value = PagingState.Loading()
            viewModel.loadNews(currentPage)
        }
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(
            object : PaginationListener<NewsUiData?>(layoutManager, adapter.state) {
                override fun loadMoreItems() {
                    currentPage++
                    adapter.state.value = PagingState.Loading()
                    viewModel.loadNews(currentPage)
                }
            }
        )

        viewModel.uiStateObserve {
            when (it) {
                is Paging.State.EmptyError -> {
                    adapter.state.value = PagingState.Error()
                }
                is Paging.State.Data<*> -> {
                    adapter.state.value = PagingState.AddData(it.data as List<NewsUiData>)
                }
                is Paging.State.FullData<*> -> {
                    adapter.state.value = PagingState.FullData()
                }
            }
        }
        viewModel.loadNews(0)
    }
}