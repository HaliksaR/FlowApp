package ru.haliksar.flowapp.features.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import ru.haliksar.flowapp.features.news.presentation.uistate.UiState
import ru.haliksar.flowapp.libraries.core.presentation.snack
import ru.haliksar.flowapp.libraries.flowbinding.clicksFlow
import ru.haliksar.flowapp.libraries.flowbinding.oneWayFlow
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
    private fun <T> StateFlow<List<T>>.listToString(scope: CoroutineScope): StateFlow<String> =
        MutableStateFlow("").apply {
            this@listToString.onEach {
                value = this@listToString.value.joinToString("\n")
            }.launchIn(scope)
        }


    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        to_sign_in.clicksFlow(lifecycleScope) {
            viewModel.navigate()
            navigate(R.id.action_newsFragment_to_signInFragment, hostId = get(named(GLOBAL_GRAPH)))
        }
        update.clicksFlow(lifecycleScope) {
            viewModel.getNews()
        }
        test.oneWayFlow(lifecycleScope, viewModel.newsFlow.listToString(lifecycleScope))
        viewModel.uiStateObserve {
            when (it) {
                UiState.Nothing -> {
                    content_container.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                }
                UiState.Loading -> {
                    content_container.visibility = View.GONE
                    loader.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    content_container.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                }
                is UiState.Error -> {
                    content_container.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                    snack("Error ${it.error.code}")
                }
            }
        }

    }
}