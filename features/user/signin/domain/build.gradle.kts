plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    coreModules(Modules.Libraries.Core.domain)
    modules(Modules.Libraries.Network.wrappers)
    coroutines()
    koin()
}