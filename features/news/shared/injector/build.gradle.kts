plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.News.Shared.domain,
        Modules.Features.News.Shared.presentation,
        Modules.Features.News.Shared.data
    )
    koin()
}