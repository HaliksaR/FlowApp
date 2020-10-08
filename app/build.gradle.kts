plugins {
    id(Plugins.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    modules(Modules.App.injector)
    modules(Modules.App.navigation)
    impl(Libs.Androidx.Core.ktx)
    impl(Libs.Androidx.Appcompat)
    koin()
    koinAndroidX()
}