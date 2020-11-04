plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(Modules.Libraries.paging)
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    impl(Libs.Androidx.Lifecycle.livedataKtx)
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    impl(Libs.Androidx.Fragment.ktx)
    impl(Libs.Androidx.Activity.ktx)
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Material)
    koin()
    coroutines()
    navigation()
}