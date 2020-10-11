plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.Quotes.domain,
        Modules.Features.Quotes.presentation,
        Modules.Features.Quotes.data
    )
    koin()
}