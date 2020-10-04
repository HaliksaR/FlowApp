plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(":libraries:retroflow")
    retrofit()
    moshiForRetrofit()
    impl(Libs.Okhttp3.logging)
    impl(Libs.Retrofit.converterScalars)
    koin()
    impl(Libs.Koin.android)
    coroutines()
}