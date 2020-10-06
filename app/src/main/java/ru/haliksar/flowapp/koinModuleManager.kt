package ru.haliksar.flowapp

import org.koin.core.KoinApplication
import ru.haliksar.flowApp.features.user.signin.injector.SingInModules
import ru.haliksar.flowapp.libraries.fakenetwork.di.FakeNetworkModule
import ru.haliksar.flowapp.libraries.network.di.NetworkModule

internal fun KoinApplication.koinModuleManager() {
    modules(NetworkModule, FakeNetworkModule)
    modules(SingInModules)
}