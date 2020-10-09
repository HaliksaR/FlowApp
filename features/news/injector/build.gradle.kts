plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.News.domain,
        Modules.Features.News.presentation,
        Modules.Features.News.data
    )
    koin()
}