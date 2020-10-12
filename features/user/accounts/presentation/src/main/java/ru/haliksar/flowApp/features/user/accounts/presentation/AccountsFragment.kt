package ru.haliksar.flowApp.features.user.accounts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.accounts_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.haliksar.flowapp.libraries.flowbinding.clicks.clicksFlow
import ru.haliksar.flowapp.libraries.flowbinding.oneway.oneWayFlow

@KoinApiExtension
class AccountsFragment : Fragment() {

    private val viewModel by viewModel<AccountsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.accounts_fragment, container, false)

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_text.oneWayFlow(
            lifecycleScope,
            viewModel.usersFlow
                .map {
                    "Table \n${it.joinToString("\n")}"
                }
        )
        sort_name_text.oneWayFlow(
            lifecycleScope,
            viewModel.byName
                .map {
                    "sort by name\n${it.joinToString("\n")}"
                }
        )
        sort_surname_text.oneWayFlow(lifecycleScope,
            viewModel.bySurname
                .map {
                    "sort by surname\n${it.joinToString("\n")}"
                }
        )
        sort_age_text.oneWayFlow(lifecycleScope,
            viewModel.byAge
                .map {
                    "sort by age\n${it.joinToString("\n")}"
                }
        )
        sort_login_text.oneWayFlow(lifecycleScope,
            viewModel.byLogin
                .map {
                    "sort by login\n${it.joinToString("\n")}"
                }
        )
        add.clicksFlow(lifecycleScope) {
            viewModel.add()
        }
        delete.clicksFlow(lifecycleScope) {
            viewModel.delete()
        }
        clear.clicksFlow(lifecycleScope) {
            viewModel.clear()
        }
    }
}