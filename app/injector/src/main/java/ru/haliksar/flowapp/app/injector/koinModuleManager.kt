@file:Suppress("EXPERIMENTAL_API_USAGE")

package ru.haliksar.flowapp.app.injector

import org.koin.core.KoinApplication
import ru.haliksar.flowApp.features.user.signin.injector.SignInModules
import ru.haliksar.flowapp.features.news.injector.NewsModules
import ru.haliksar.flowapp.libraries.fakenetwork.di.FakeNetworkModule
import ru.haliksar.flowapp.libraries.network.di.NetworkModule
import ru.haliksar.flowapp.navigation.NavigationModule

fun KoinApplication.koinModuleManager() {
    modules(NavigationModule)
    modules(NetworkModule, FakeNetworkModule)
    modules(SignInModules)
    modules(NewsModules)
}