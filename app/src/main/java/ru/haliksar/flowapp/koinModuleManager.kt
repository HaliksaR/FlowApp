package ru.haliksar.flowapp

import org.koin.core.KoinApplication
import ru.haliksar.flowapp.libraries.network.NetworkModule

internal fun KoinApplication.koinModuleManager() {
    modules(NetworkModule)
}