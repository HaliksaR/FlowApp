@file:Suppress("EXPERIMENTAL_API_USAGE")

package ru.haliksar.flowapp.libraries.core.injector

import ru.haliksar.flowapp.libraries.core.data.di.CoreDataModules
import ru.haliksar.flowapp.libraries.core.presentation.di.CoreDataMappersModule


val CoreModules =
    listOf(
        CoreDataMappersModule
    ) + CoreDataModules