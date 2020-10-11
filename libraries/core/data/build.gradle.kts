plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    modules(
        Modules.Libraries.Core.domain
    )
    coroutines()
    gson()
    koin()
}