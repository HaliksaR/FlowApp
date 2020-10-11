package ru.haliksar.flowapp.features.quotes.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.quotes_fragment.*
import kotlinx.android.synthetic.main.quotes_item.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.quotes.presentation.paging3.QuotesAdapter


@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
@KoinApiExtension
class QuotesFragment : Fragment() {

    private val viewModel by viewModel<QuotesViewModel>()

    private val paging3Adapter by lazy {
        QuotesAdapter { view, _ ->
            view.profileUrl.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.quotes_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_paging3.layoutManager = LinearLayoutManager(requireContext())
        rv_paging3.adapter = paging3Adapter
        swipeToRefresh.setOnRefreshListener {
            paging3Adapter.refresh()
            swipeToRefresh.isRefreshing = false
        }
        viewModel.fetchQuotes().distinctUntilChanged().onEach {
            paging3Adapter.submitData(lifecycle, it)
        }.launchIn(lifecycleScope)
    }
}