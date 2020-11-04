plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(
        Modules.Libraries.Core.presentation,
        Modules.Libraries.fllowbinding
    )
    modules(
        Modules.Features.Quotes.domain,
        Modules.Features.Quotes.data
    )
    sharedModules(
        Modules.Features.News.Shared.data,
        Modules.Features.News.Shared.domain
    )
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    impl(Libs.Androidx.Recyclerview)
    impl(Libs.Androidx.Swiperefreshlayout)
    impl(Libs.Adapterdelegates4)
    impl(Libs.Coil)
    impl(Libs.Androidx.Paging.runtime)
    fragment()
    navigation()
    impl(Libs.Material)
    coroutines()
    koin()
    koinAndroidX()
}