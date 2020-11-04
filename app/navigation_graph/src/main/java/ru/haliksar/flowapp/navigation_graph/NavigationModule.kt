package ru.haliksar.flowapp.navigation_graph

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.haliksar.flowapp.app.navigation_graph.R

const val GLOBAL_GRAPH = "GLOBAL_GRAPH"

val NavigationModule = module {
    single(named(GLOBAL_GRAPH)) { R.id.fragmentNavHost }
}