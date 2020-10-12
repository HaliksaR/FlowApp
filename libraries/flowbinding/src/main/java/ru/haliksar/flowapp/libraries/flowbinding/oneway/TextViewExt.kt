package ru.haliksar.flowapp.libraries.flowbinding.oneway

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
inline fun TextView.oneWayFlow(
    flow: Flow<String?>,
    crossinline take: (CharSequence?) -> Unit = {}
) = flow
    .filter { it != text?.toString() }
    .onEach {
        text = it
        take(text)
    }

@ExperimentalCoroutinesApi
inline fun TextView.oneWayFlow(
    scope: CoroutineScope,
    flow: Flow<String?>,
    crossinline take: (CharSequence?) -> Unit = {}
) = oneWayFlow(flow, take).launchIn(scope)

@ExperimentalCoroutinesApi
inline fun TextView.oneWayFlow(
    owner: LifecycleOwner,
    flow: Flow<String?>,
    crossinline take: (CharSequence?) -> Unit = {}
) = oneWayFlow(owner.lifecycle.coroutineScope, flow, take)