package ru.haliksar.flowapp.app.injector

import org.koin.core.KoinApplication
import ru.haliksar.flowapp.libraries.fakenetwork.di.FakeNetworkModule
import ru.haliksar.flowapp.libraries.network.di.NetworkModule
import ru.haliksar.flowapp.navigation_graph.NavigationModule

fun KoinApplication.koinModuleManager() {
    modules(NavigationModule)
    modules(NetworkModule, FakeNetworkModule)
}