package ru.haliksar.flowapp.libraries.core.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
abstract class BaseViewModel : ViewModel(), LifecycleObserver, KoinComponent