plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Libraries.Network,
        Modules.Features.User.SignIn.data,
        Modules.Features.News.data,
        Modules.Features.Quotes.data
    )
    implementation(fileTree("$rootDir/libs") { include(Libs.HaliksaR.fakeOkHttpInterceptor) })
    retrofit()
    gsonRetrofit()
    impl(Libs.Okhttp3.logging)
    impl(Libs.Retrofit.converterScalars)
    koin()
    koinAndroidX()
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Koin.android)
    coroutines()
}