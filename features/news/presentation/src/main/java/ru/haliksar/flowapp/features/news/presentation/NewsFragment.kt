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
import ru.haliksar.flowapp.features.news.presentation.paging.PostRecyclerAdapter
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.paging.Paging
import ru.haliksar.flowapp.navigation.GLOBAL_GRAPH
import ru.haliksar.flowapp.navigation.navigate


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

    private var adapter: PostRecyclerAdapter? = null
    private var currentPage: Int = PAGE_START
    private var isLastPage = false
    private val totalPage = 10
    private var isLoading = false
    private var isError = false
    private var isFull = false
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
            isLastPage = false
            adapter?.clear()
            viewModel.loadNews(currentPage)
        }
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = PostRecyclerAdapter(ArrayList())
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                isError = false
                isFull = false
                isLoading = true
                currentPage++
                viewModel.loadNews(currentPage)
            }

            override val isLastPage: Boolean = this@NewsFragment.isLastPage
            override var isLoading: Boolean = this@NewsFragment.isLoading
            override var isError: Boolean = this@NewsFragment.isError
            override var isFull: Boolean = this@NewsFragment.isFull
        })

        viewModel.uiStateObserve {
            when (it) {
                is Paging.State.EmptyError -> {
                    isError = true
                    adapter?.addError()
                }
                is Paging.State.Data<*> -> {
                    if (currentPage != PAGE_START) {
                        adapter?.removeLoading()
                    }
                    adapter?.addItems(it.data as List<NewsUiData>)
                    if (currentPage < totalPage) {
                        adapter?.addLoading()
                    } else {
                        isLastPage = true
                    }
                    isLoading = false
                }
                is Paging.State.FullData<*> -> {
                    isFull = true
                    adapter?.addFull()
                }
            }
        }

        viewModel.loadNews(0)
    }
}