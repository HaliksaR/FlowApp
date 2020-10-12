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
        Modules.Features.User.Accounts.domain,
        Modules.Features.User.Accounts.data,
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