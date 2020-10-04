plugins {
    id(Plugins.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    modules(Modules.Libraries.core)
    impl(Libs.Androidx.Core.ktx)
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Androidx.Constraintlayout)
    hilt()
    kotlin()
}