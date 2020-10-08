package ru.haliksar.flowapp.app.injector

import org.koin.core.KoinApplication
import ru.haliksar.flowApp.features.user.signin.injector.SingInModules
import ru.haliksar.flowapp.libraries.fakenetwork.di.FakeNetworkModule
import ru.haliksar.flowapp.libraries.network.di.NetworkModule

fun KoinApplication.koinModuleManager() {
    modules(NetworkModule, FakeNetworkModule)
    modules(SingInModules)
}