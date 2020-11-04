package ru.haliksar.flowapp.libraries.flowbinding.oneway

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
inline fun <T : View, S> T.oneWayFlowC(
    flow: Flow<S>,
    crossinline setter: T.(S) -> Unit
) = flow.onEach {
    setter(it)
}

@ExperimentalCoroutinesApi
inline fun <T : View, S> T.oneWayFlowC(
    scope: CoroutineScope,
    flow: Flow<S>,
    crossinline setter: T.(S) -> Unit
) = oneWayFlowC<T, S>(flow, setter).launchIn(scope)

@ExperimentalCoroutinesApi
inline fun <T : View, S> T.oneWayFlowC(
    owner: LifecycleOwner,
    flow: Flow<S>,
    crossinline setter: T.(S) -> Unit
) = oneWayFlowC<T, S>(flow, setter).launchIn(owner.lifecycle.coroutineScope)