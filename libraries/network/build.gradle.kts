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
    retrofit()
    gsonRetrofit()
    impl(Libs.Okhttp3.logging)
    impl(Libs.Retrofit.converterScalars)
    koin()
    rxKotlin()
    rxRetrofit()
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Koin.android)
    coroutines()
}