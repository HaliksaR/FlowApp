package ru.haliksar.flowapp.libraries.flowbinding.twoway

import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.haliksar.flowapp.libraries.flowbinding.asflow.asFlow

@ExperimentalCoroutinesApi
inline fun EditText.twoWayFlow(
    scope: CoroutineScope,
    flow: MutableStateFlow<String>,
    crossinline take: (CharSequence) -> Unit = {}
) {
    asFlow {
        if (it.trim() != flow.value) {
            flow.value = it.trim().toString()
            take(it)
        }
    }.launchIn(scope)
    flow.onEach {
        if (text != null && it != text.toString()) {
            setText(it)
        }
    }.launchIn(scope)
}

@ExperimentalCoroutinesApi
inline fun EditText.twoWayFlow(
    owner: LifecycleOwner,
    flow: MutableStateFlow<String>,
    crossinline take: (CharSequence) -> Unit = {}
) = twoWayFlow(owner.lifecycle.coroutineScope, flow, take)