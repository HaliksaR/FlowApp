package ru.haliksar.flowApp.features.user.signin.presentation.ext

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
fun EditText.asFlow(scope: CoroutineScope, take: (CharSequence) -> Unit) {
    callbackFlow {
        val watcher = doAfterTextChanged { offer(it.toString()) }
        awaitClose { removeTextChangedListener(watcher) }
    }.onEach { take(it) }.launchIn(scope)
}