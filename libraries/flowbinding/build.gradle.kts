plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(Modules.Libraries.Core.presentation)
    coroutines()
    fragment()
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    impl(Libs.Material)
}