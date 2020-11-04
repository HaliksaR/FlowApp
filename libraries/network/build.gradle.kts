plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.retroFlow,
        Modules.Libraries.Network.wrappers
    )
    api(Libs.Retrofit)
    api(Libs.Gson)
    impl(Libs.Gson.converter)
    impl(Libs.Okhttp3.logging)
    impl(Libs.Retrofit.converterScalars)
    impl(Libs.Koin.android)
    koin()
    coroutines()
}