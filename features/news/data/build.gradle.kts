plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.Core.data,
        Modules.Libraries.Network,
        Modules.Libraries.Network.wrappers,
        Modules.Libraries.Network.pagingwrappers,
        Modules.Features.News.domain
    )
    retrofit()
    impl(Libs.Androidx.Paging.runtime)
    coroutines()
    gson()
    koin()
}