plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(
        Modules.Libraries.Core.data
    )
    modules(
        Modules.Libraries.Network,
        Modules.Libraries.Network.wrappers,
        Modules.Libraries.Network.pagingwrappers,
        Modules.Features.Quotes.domain
    )
    sharedModules(
        Modules.Features.News.Shared.domain,
        Modules.Features.News.Shared.data
    )
    retrofit()
    impl(Libs.Androidx.Paging.runtime)
    coroutines()
    gson()
    koin()
}