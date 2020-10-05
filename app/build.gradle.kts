plugins {
    id(Plugins.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    modules(
        Modules.Libraries.core,
        Modules.Libraries.network,
        Modules.Libraries.fakeNetwork,
        Modules.Libraries.retroFlow
    )
    impl(Libs.Androidx.Core.ktx)
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Androidx.Constraintlayout)
    koin()
    gson()
    rxKotlin()
    rxAndroid()
    retrofit()
    koinAndroidX()
}