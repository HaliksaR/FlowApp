package ru.haliksar.flowapp.features.news.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.presentation.bind.bind
import ru.haliksar.flowapp.features.news.presentation.databinding.NewsFragmentBinding
import ru.haliksar.flowapp.features.news.presentation.di.injector.NewsDynamicInjector
import ru.haliksar.flowapp.features.news.presentation.paging.NewsAdapterDelegate
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector
import ru.haliksar.flowapp.libraries.core.presentation.base.KoinFragment
import ru.haliksar.flowapp.libraries.paging.mutable.PagingMutableAdapter

@KoinApiExtension
class NewsFragment(
    dynamicInjector: DynamicInjector = NewsDynamicInjector(),
) : KoinFragment<NewsFragmentBinding>(dynamicInjector) {

    val viewModel: NewsViewModel by viewModel()

    val adapter by lazy {
        PagingMutableAdapter(
            nextPageCallback = {
                viewModel.loadMore()
            },
            itemDiff = { oldItem, newItem ->
                if (oldItem is NewsEntity && newItem is NewsEntity) {
                    oldItem.id == newItem.id
                } else {
                    false
                }
            },
            delegate = NewsAdapterDelegate { binding, _ ->
                binding.profileUrl.movementMethod = LinkMovementMethod.getInstance()
            },
        )
    }

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = NewsFragmentBinding.inflate(inflater, container, false)

    override fun otherSetups() {
        bind()
    }
}