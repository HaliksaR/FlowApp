package ru.haliksar.flowapp.libraries.flowbinding.oneway

import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
inline fun EditText.oneWayFlow(
    dataFlow: StateFlow<String?>,
    crossinline take: (CharSequence) -> Unit = {}
) = dataFlow.onEach {
    if (text != null && it != null && it != text?.toString()) {
        setText(it)
        take(text)
    }
}

@ExperimentalCoroutinesApi
inline fun EditText.oneWayFlow(
    scope: CoroutineScope,
    dataFlow: StateFlow<String?>,
    crossinline take: (CharSequence) -> Unit = {}
) = oneWayFlow(dataFlow, take).launchIn(scope)

@ExperimentalCoroutinesApi
inline fun EditText.oneWayFlow(
    owner: LifecycleOwner,
    dataFlow: StateFlow<String?>,
    crossinline take: (CharSequence) -> Unit = {}
) = oneWayFlow(owner.lifecycle.coroutineScope, dataFlow, take)