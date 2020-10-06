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
        Modules.Libraries.Network.wrappers
    )
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    impl(Libs.Androidx.Lifecycle.livedataKtx)
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    impl(Libs.Androidx.Fragment.ktx)
    impl(Libs.Androidx.Fragment)
    impl(Libs.Androidx.Fragment.testing)
    coroutines()
    koin()
    koinAndroidX()
}