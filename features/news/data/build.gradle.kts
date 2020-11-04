plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(Modules.Libraries.Network)
    modules(Modules.Features.News.domain)
    sharedModules(
        Modules.Features.News.Shared.domain,
        Modules.Features.News.Shared.data
    )
    coroutines()
    koin()
}