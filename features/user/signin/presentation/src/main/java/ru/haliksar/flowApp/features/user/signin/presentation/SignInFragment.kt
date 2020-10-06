package ru.haliksar.flowApp.features.user.signin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.sign_in_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowApp.features.user.signin.presentation.ext.asFlow
import ru.haliksar.flowApp.features.user.signin.presentation.ext.clicksFlow
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.UiState
import ru.haliksar.flowapp.libraries.core.presentation.snack
import ru.haliksar.flowapp.libraries.core.presentation.toast

@KoinApiExtension
class SignInFragment : Fragment() {

    private val viewModel by viewModel<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sign_in_fragment, container, false)


    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_text_login.asFlow(lifecycleScope) {
            viewModel.setLogin(it.toString())
        }
        edit_text_password.asFlow(lifecycleScope) {
            viewModel.setPassword(it.toString())
        }
        button_sign_in.clicksFlow(lifecycleScope, true) {
            viewModel.startSignIn()
        }
        viewModel.uiStateObserve(viewLifecycleOwner) {
            when (it) {
                UiState.Input -> {
                    content_container.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                }
                UiState.Loading -> {
                    content_container.visibility = View.GONE
                    loader.visibility = View.VISIBLE
                    toast("Loading")
                }
                is UiState.Success -> {
                    content_container.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                    toast("Success ${it.data.accessToken}")
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