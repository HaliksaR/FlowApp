package ru.haliksar.flowApp.features.user.signin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowApp.features.user.signin.presentation.bind.bind
import ru.haliksar.flowApp.features.user.signin.presentation.databinding.SignInFragmentBinding
import ru.haliksar.flowApp.features.user.signin.presentation.di.injector.SignInDynamicInjector
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector
import ru.haliksar.flowapp.libraries.core.presentation.base.KoinFragment

@ExperimentalCoroutinesApi
@KoinApiExtension
class SignInFragment(
    dynamicInjector: DynamicInjector = SignInDynamicInjector()
) : KoinFragment<SignInFragmentBinding>(dynamicInjector) {

    val viewModel: SignInViewModel by viewModel()

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SignInFragmentBinding.inflate(inflater, container, false)

    override fun otherSetups() {
        bind()
    }
}