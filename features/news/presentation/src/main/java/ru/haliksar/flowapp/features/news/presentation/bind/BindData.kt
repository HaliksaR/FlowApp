package ru.haliksar.flowapp.features.news.presentation.bind

import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.news.presentation.NewsFragment
import ru.haliksar.flowapp.libraries.core.presentation.ext.snack

@KoinApiExtension
fun NewsFragment.bind() {
    with(binding) {
        pagingView.adapter = adapter
        pagingView.refreshCallback = viewModel::refresh
        pagingView.itemMoved = viewModel::onMove
        pagingView.itemRemoved = viewModel::onRemove
        viewModel.pagingStateObserve {
            pagingView.render(it)
        }
        viewModel.observeErrors {
            it?.let { snack(it.message.toString()) }
        }
    }
}