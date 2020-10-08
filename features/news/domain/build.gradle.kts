plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    modules(
        Modules.Libraries.Network.wrappers,
        Modules.Libraries.Core.domain
    )
    coroutines()
    koin()
}