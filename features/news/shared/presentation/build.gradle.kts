plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(Modules.Libraries.Core.data)
    sharedModules(
        Modules.Features.News.Shared.data,
        Modules.Features.News.Shared.domain
    )
    koin()
    coroutines()
}