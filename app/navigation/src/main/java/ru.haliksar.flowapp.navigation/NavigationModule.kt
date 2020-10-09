package ru.haliksar.flowapp.navigation

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.app.navigation.R

const val GLOBAL_GRAPH = "GLOBAL_GRAPH"

val NavigationModule = module {
    single(named(GLOBAL_GRAPH)) { R.id.host_global }
}