plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    coreModules(Modules.Libraries.Core.domain)
    sharedModules(Modules.Features.News.Shared.domain)
    coroutines()
    koin()
}