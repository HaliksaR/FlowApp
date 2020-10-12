plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    coreModules(Modules.Libraries.Core.data)
    sharedModules(Modules.Features.News.Shared.domain)
    coroutines()
    gson()
    koin()
}