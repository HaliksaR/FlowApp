plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.Core.data,
        Modules.Libraries.Network,
        Modules.Libraries.Network.wrappers
    )
    retrofit()
    coroutines()
    gson()
    koin()
}