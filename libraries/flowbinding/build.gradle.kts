plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(Modules.Libraries.Core.presentation)
    coroutines()
    fragment()
    impl(Libs.Androidx.Lifecycle.runtimeKtx)
    impl(Libs.Material)
}