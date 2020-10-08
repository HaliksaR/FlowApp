package ru.haliksar.flowapp.features.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.libraries.flowbinding.clicksFlow
import ru.haliksar.flowapp.navigation.GLOBAL_GRAPH
import ru.haliksar.flowapp.navigation.navigate

@KoinApiExtension
class NewsFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.news_fragment, container, false)

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        to_sign_in.clicksFlow(lifecycleScope) {
            navigate(R.id.action_newsFragment_to_signInFragment, hostId = get(named(GLOBAL_GRAPH)))
        }
    }
}