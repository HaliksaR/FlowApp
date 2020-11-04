package ru.haliksar.flowApp.features.user.accounts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowApp.features.user.accounts.presentation.bind.bind
import ru.haliksar.flowApp.features.user.accounts.presentation.databinding.AccountsFragmentBinding
import ru.haliksar.flowApp.features.user.accounts.presentation.di.injector.AccountsDynamicInjector
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector
import ru.haliksar.flowapp.libraries.core.presentation.base.KoinFragment

@KoinApiExtension
class AccountsFragment(
    dynamicInjector: DynamicInjector = AccountsDynamicInjector()
) : KoinFragment<AccountsFragmentBinding>(dynamicInjector) {

    val viewModel: AccountsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.accounts_fragment, container, false)

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = AccountsFragmentBinding.inflate(inflater, container, false)


    override fun otherSetups() {
        bind()
    }
}