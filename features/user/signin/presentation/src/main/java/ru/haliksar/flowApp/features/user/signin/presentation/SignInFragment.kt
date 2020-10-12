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
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.UiState
import ru.haliksar.flowapp.libraries.core.presentation.ext.snack
import ru.haliksar.flowapp.libraries.core.presentation.ext.toast
import ru.haliksar.flowapp.libraries.flowbinding.clicks.clicksFlow
import ru.haliksar.flowapp.libraries.flowbinding.oneway.oneWayFlowC
import ru.haliksar.flowapp.libraries.flowbinding.twoway.twoWayFlow

@KoinApiExtension
class SignInFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sign_in_fragment, container, false)

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            textView.oneWayFlowC(lifecycleScope, signInData.login) {
                text = it
            }
            edit_text_login.twoWayFlow(lifecycleScope, signInData.login)
            edit_text_password.twoWayFlow(lifecycleScope, signInData.password)
            button_sign_in.clicksFlow(lifecycleScope, true) {
                startSignIn()
            }
            uiStateObserve {
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
}