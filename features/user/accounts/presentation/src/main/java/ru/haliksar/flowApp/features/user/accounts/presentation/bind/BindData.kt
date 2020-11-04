package ru.haliksar.flowApp.features.user.accounts.presentation.bind

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowApp.features.user.accounts.presentation.AccountsFragment
import ru.haliksar.flowapp.libraries.flowbinding.clicks.clicksFlow
import ru.haliksar.flowapp.libraries.flowbinding.oneway.oneWayFlow

@KoinApiExtension
fun AccountsFragment.bind() {
    with(binding) {
        mainText.oneWayFlow(
            lifecycleScope,
            viewModel.usersFlow.map { "Table \n${it.joinToString("\n")}" }
        )
        sortNameText.oneWayFlow(
            lifecycleScope,
            viewModel.byName.map { "sort by name\n${it.joinToString("\n")}" }
        )
        sortSurnameText.oneWayFlow(
            lifecycleScope,
            viewModel.bySurname.map { "sort by surname\n${it.joinToString("\n")}" }
        )
        sortAgeText.oneWayFlow(
            lifecycleScope,
            viewModel.byAge.map { "sort by age\n${it.joinToString("\n")}" }
        )
        sortLoginText.oneWayFlow(
            lifecycleScope,
            viewModel.byLogin.map { "sort by login\n${it.joinToString("\n")}" }
        )
        add.clicksFlow(lifecycleScope) { viewModel.add() }
        delete.clicksFlow(lifecycleScope) { viewModel.delete() }
        clear.clicksFlow(lifecycleScope) { viewModel.clear() }
    }
}