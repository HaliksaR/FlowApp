plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    sharedModules(Modules.Features.News.Shared.domain)
    coroutines()
    koin()
}