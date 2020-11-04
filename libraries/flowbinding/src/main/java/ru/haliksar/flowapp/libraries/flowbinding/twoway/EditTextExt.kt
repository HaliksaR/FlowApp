package ru.haliksar.flowapp.libraries.flowbinding.twoway

import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.haliksar.flowapp.libraries.flowbinding.asflow.asFlow

@ExperimentalCoroutinesApi
inline fun EditText.twoWayFlow(
    scope: CoroutineScope,
    flow: MutableStateFlow<String>,
    crossinline take: (CharSequence?) -> Unit = {}
) {
    asFlow()
        .filterNotNull()
        .filter {
            it.trim() != flow.value
        }.onEach {
            flow.value = it.trim()
            take(it)
        }.launchIn(scope)
    flow
        .filter {
            text != null && it != text.toString()
        }.onEach {
            setText(it)
        }.launchIn(scope)
}

@ExperimentalCoroutinesApi
inline fun EditText.twoWayFlow(
    owner: LifecycleOwner,
    flow: MutableStateFlow<String>,
    crossinline take: (CharSequence?) -> Unit = {}
) = twoWayFlow(owner.lifecycle.coroutineScope, flow, take)

@ExperimentalCoroutinesApi
inline fun EditText.twoWayFlowN(
    scope: CoroutineScope,
    flow: MutableStateFlow<String?>,
    crossinline take: (CharSequence?) -> Unit = {}
) {
    asFlow()
        .filter {
            it?.trim() != flow.value
        }.onEach {
            flow.value = it?.trim()
            take(it)
        }.launchIn(scope)
    flow
        .filter {
            text != null && it != text.toString()
        }.onEach {
            setText(it)
        }.launchIn(scope)
}

@ExperimentalCoroutinesApi
inline fun EditText.twoWayFlowN(
    owner: LifecycleOwner,
    flow: MutableStateFlow<String?>,
    crossinline take: (CharSequence?) -> Unit = {}
) = twoWayFlowN(owner.lifecycle.coroutineScope, flow, take)
