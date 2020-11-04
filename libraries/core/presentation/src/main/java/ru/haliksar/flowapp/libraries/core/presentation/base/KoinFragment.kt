package ru.haliksar.flowapp.libraries.core.presentation.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
abstract class KoinFragment<VB : ViewBinding>(vararg injectors: DynamicInjector) :
    BindingFragment<VB>(),
    KoinComponent {

    private val injectors = injectors.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectors.forEach { it.inject() }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        injectors.forEach { it.clear() }
        super.onDestroy()
    }
}