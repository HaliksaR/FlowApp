package ru.haliksar.flowapp.libraries.flowbinding

import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun EditText.asFlow(take: (CharSequence) -> Unit) =
    callbackFlow {
        val watcher = doAfterTextChanged {
            it?.let { offer(it.toString()) }
        }
        awaitClose { removeTextChangedListener(watcher) }
    }.onEach { take(it) }


@ExperimentalCoroutinesApi
fun EditText.asFlow(scope: CoroutineScope, take: (CharSequence) -> Unit) =
    asFlow(take).launchIn(scope)

@ExperimentalCoroutinesApi
fun EditText.twoWayFlow(
    scope: CoroutineScope,
    dataFlow: MutableStateFlow<String>,
    take: (CharSequence) -> Unit = {}
) {
    asFlow {
        if (it.toString().trim() != dataFlow.value) {
            dataFlow.value = it.toString().trim()
        }
        take(it)
    }.launchIn(scope)
    dataFlow.onEach {
        if (text != null && it != text.toString()) {
            setText(it)
        }
    }.launchIn(scope)
}

@ExperimentalCoroutinesApi
fun EditText.oneWayFlow(
    scope: CoroutineScope,
    dataFlow: MutableStateFlow<String>
) {
    dataFlow.onEach {
        if (text != null && it != text.toString()) {
            setText(it)
        }
    }.launchIn(scope)
}

@ExperimentalCoroutinesApi
fun TextView.oneWayFlow(
    scope: CoroutineScope,
    dataFlow: StateFlow<String>
) {
    dataFlow.onEach {
        if (it != text?.toString()) {
            text = it
        }
    }.launchIn(scope)
}