plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(Modules.Libraries.Network.wrappers)
    retrofit()
    gsonRetrofit()
    impl(Libs.Androidx.Paging.common)
    koin()
    coroutines()
}