package ru.haliksar.flowApp.features.user.signin.presentation.ext

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.haliksar.flowapp.libraries.core.presentation.hideKeyboard

@ExperimentalCoroutinesApi
fun View.clicksFlow(scope: CoroutineScope, closeKeyBoard: Boolean, action: () -> Unit) {
    callbackFlow {
        setOnClickListener { offer(it) }
        awaitClose { setOnClickListener(null) }
    }.onEach {
        if (closeKeyBoard) {
            hideKeyboard()
        }
        action()
    }.launchIn(scope)
}