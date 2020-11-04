plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(
        Modules.Libraries.Core.presentation
    )
    modules(
        Modules.Features.News.domain,
        Modules.Features.News.data,
        Modules.Libraries.Network.wrappers,
        Modules.Libraries.fllowbinding,
        Modules.Libraries.paging
    )
    sharedModules(
        Modules.Features.News.Shared.domain,
        Modules.Features.News.Shared.data
    )
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    impl(Libs.Androidx.Lifecycle.livedataKtx)
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    impl(Libs.Androidx.Recyclerview)
    impl(Libs.Androidx.Swiperefreshlayout)
    impl(Libs.Adapterdelegates4)
    impl(Libs.Coil)
    fragment()
    navigation()
    impl(Libs.Material)
    coroutines()
    koin()
    koinAndroidX()
}