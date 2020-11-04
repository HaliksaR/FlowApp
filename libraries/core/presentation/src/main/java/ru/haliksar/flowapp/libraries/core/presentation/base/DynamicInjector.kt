package ru.haliksar.flowapp.libraries.core.presentation.base

import android.util.Log
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

@KoinApiExtension
open class DynamicInjector(private var modules: List<Module>) : KoinComponent {
    var isInject = false
        private set

    fun inject() {
        if (!isInject) {
            Log.d("DynamicInjector", "inject $")
            loadKoinModules(modules)
            isInject = true
        }
    }

    fun clear() {
        Log.d("DynamicInjector", "clear")
        unloadKoinModules(modules).also { isInject = false }
    }
}