package ru.haliksar.flowApp.features.user.signin.presentation.bind

import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowApp.features.user.signin.presentation.SignInFragment
import ru.haliksar.flowApp.features.user.signin.presentation.uistate.UiState
import ru.haliksar.flowapp.libraries.core.presentation.ext.snack
import ru.haliksar.flowapp.libraries.core.presentation.ext.toast
import ru.haliksar.flowapp.libraries.flowbinding.clicks.clicksFlow
import ru.haliksar.flowapp.libraries.flowbinding.oneway.oneWayFlowC
import ru.haliksar.flowapp.libraries.flowbinding.twoway.twoWayFlow

@ExperimentalCoroutinesApi
@KoinApiExtension
fun SignInFragment.bind() {
    with(binding) {
        with(viewModel) {
            textView.oneWayFlowC(lifecycleScope, signInData.login) {
                text = it
            }
            editTextLogin.twoWayFlow(lifecycleScope, signInData.login)
            editTextPassword.twoWayFlow(lifecycleScope, signInData.password)
            buttonSignIn.clicksFlow(lifecycleScope, true) {
                startSignIn()
            }
            uiStateObserve {
                when (it) {
                    UiState.Input -> {
                        contentContainer.visibility = View.VISIBLE
                        loader.visibility = View.GONE
                    }
                    UiState.Loading -> {
                        contentContainer.visibility = View.GONE
                        loader.visibility = View.VISIBLE
                        toast("Loading")
                    }
                    is UiState.Success -> {
                        contentContainer.visibility = View.VISIBLE
                        loader.visibility = View.GONE
                        toast("Success ${it.data.accessToken}")
                    }
                    is UiState.Error -> {
                        contentContainer.visibility = View.VISIBLE
                        loader.visibility = View.GONE
                        snack("Error ${it.error.code}")
                    }
                }
            }
        }
    }
}