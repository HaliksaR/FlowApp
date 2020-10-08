plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.User.SignIn.domain,
        Modules.Features.User.SignIn.data,
        Modules.Libraries.Core,
        Modules.Libraries.Core.data,
        Modules.Libraries.Core.domain,
        Modules.Libraries.Network.wrappers,
        Modules.Libraries.fllowbinding
    )
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    impl(Libs.Androidx.Lifecycle.livedataKtx)
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    fragment()
    navigation()
    impl(Libs.Material)
    coroutines()
    koin()
    koinAndroidX()
}