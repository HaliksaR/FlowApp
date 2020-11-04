package ru.haliksar.flowapp.features.quotes.presentation.bind

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.quotes.presentation.QuotesFragment

@KoinApiExtension
fun QuotesFragment.bind() {
    with(binding) {
        rvPaging3.layoutManager = LinearLayoutManager(requireContext())
        rvPaging3.adapter = paging3Adapter
        swipeToRefresh.setOnRefreshListener {
            paging3Adapter.refresh()
            swipeToRefresh.isRefreshing = false
        }
        viewModel.fetchQuotes().distinctUntilChanged().onEach {
            paging3Adapter.submitData(lifecycle, it)
        }.launchIn(lifecycleScope)
    }
}