package ru.haliksar.flowapp.features.quotes.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.features.quotes.presentation.bind.bind
import ru.haliksar.flowapp.features.quotes.presentation.databinding.QuotesFragmentBinding
import ru.haliksar.flowapp.features.quotes.presentation.di.injector.QuotesDynamicInjector
import ru.haliksar.flowapp.features.quotes.presentation.paging3.QuotesAdapter
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector
import ru.haliksar.flowapp.libraries.core.presentation.base.KoinFragment


@KoinApiExtension
class QuotesFragment(
    dynamicInjector: DynamicInjector = QuotesDynamicInjector()
) : KoinFragment<QuotesFragmentBinding>(dynamicInjector) {

    val viewModel: QuotesViewModel by viewModel()

    val paging3Adapter by lazy {
        QuotesAdapter { binding, _ ->
            binding.profileUrl.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = QuotesFragmentBinding.inflate(inflater, container, false)

    override fun otherSetups() {
        bind()
    }
}