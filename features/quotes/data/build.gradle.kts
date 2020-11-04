plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(
        Modules.Libraries.Network,
        Modules.Libraries.Network.pagingwrappers
    )
    sharedModules(
        Modules.Features.News.Shared.domain,
        Modules.Features.News.Shared.data
    )
    modules(Modules.Features.Quotes.domain)
    impl(Libs.Androidx.Paging.runtime)
    coroutines()
    koin()
}