plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    impl(Libs.Kotlin.stdlib)
    impl(Libs.Retrofit)
    impl(Libs.Kotlin.coroutines)
}