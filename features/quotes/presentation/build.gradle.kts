plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(
        Modules.Libraries.Core.presentation,
        Modules.Libraries.Core.data,
        Modules.Libraries.Core.domain
    )
    modules(
        Modules.Features.Quotes.domain,
        Modules.Features.Quotes.data,
        Modules.Libraries.Network.wrappers,
        Modules.App.navigation,
        Modules.Libraries.fllowbinding
    )
    sharedModules(
        Modules.Features.News.Shared.data,
        Modules.Features.News.Shared.domain,
        Modules.Features.News.Shared.presentation
    )
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    impl(Libs.Androidx.Lifecycle.livedataKtx)
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