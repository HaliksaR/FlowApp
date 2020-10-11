plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.Core.domain,
        Modules.Libraries.Core.presentation,
        Modules.Libraries.Core.data
    )
    koin()
}