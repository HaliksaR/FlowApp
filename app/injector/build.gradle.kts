plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.fakeNetwork,
        Modules.Libraries.Network,
        Modules.App.navigation
    )
    koin()
}