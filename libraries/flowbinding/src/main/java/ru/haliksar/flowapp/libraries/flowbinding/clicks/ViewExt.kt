package ru.haliksar.flowapp.libraries.flowbinding.clicks

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.haliksar.flowapp.libraries.core.presentation.ext.hideKeyboard

@ExperimentalCoroutinesApi
inline fun View.clicksFlow(
    closeKeyBoard: Boolean = false,
    crossinline action: () -> Unit
) = callbackFlow {
    setOnClickListener {
        offer(it)
    }
    awaitClose {
        setOnClickListener(null)
    }
}.onEach {
    if (closeKeyBoard) {
        hideKeyboard()
    }
    action()
}

@ExperimentalCoroutinesApi
inline fun View.clicksFlow(
    scope: CoroutineScope,
    closeKeyBoard: Boolean = false,
    crossinline action: () -> Unit
) = clicksFlow(closeKeyBoard, action).launchIn(scope)

@ExperimentalCoroutinesApi
inline fun View.clicksFlow(
    owner: LifecycleOwner,
    closeKeyBoard: Boolean = false,
    crossinline action: () -> Unit
) = clicksFlow(owner.lifecycle.coroutineScope, closeKeyBoard, action)