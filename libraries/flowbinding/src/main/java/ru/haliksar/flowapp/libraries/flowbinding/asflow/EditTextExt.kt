package ru.haliksar.flowapp.libraries.flowbinding.asflow

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
inline fun EditText.asFlow(
    crossinline take: (CharSequence) -> Unit
) = callbackFlow {
    val watcher = doAfterTextChanged { offer(it.toString()) }
    awaitClose { removeTextChangedListener(watcher) }
}.onEach { take(it) }

@ExperimentalCoroutinesApi
inline fun EditText.asFlow(
    scope: CoroutineScope,
    crossinline take: (CharSequence) -> Unit
) = asFlow(take).launchIn(scope)

@ExperimentalCoroutinesApi
inline fun EditText.asFlow(
    owner: LifecycleOwner,
    crossinline take: (CharSequence) -> Unit
) = asFlow(take).launchIn(owner.lifecycle.coroutineScope)