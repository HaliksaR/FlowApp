plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.News.domain,
        Modules.Features.News.data,
        Modules.Libraries.Core.presentation,
        Modules.Libraries.Core.data,
        Modules.Libraries.Core.domain,
        Modules.Libraries.Network.wrappers,
        Modules.App.navigation,
        Modules.Libraries.fllowbinding,
        Modules.Libraries.paging
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