plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    modules(
        Modules.Libraries.Core.data
    )
    kotlin()
    coroutines()
    retrofit()
}