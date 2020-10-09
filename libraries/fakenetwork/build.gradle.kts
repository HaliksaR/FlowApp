plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.Network,
        Modules.Libraries.Network.wrappers,
        Modules.Features.User.SignIn.data,
        Modules.Features.News.data
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