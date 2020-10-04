plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.toString())
}

group = "ru.haliksar.retroflowcalladapter"

dependencies {
    impl(Libs.Kotlin.stdlib)
    impl(Libs.Retrofit)
    impl(Libs.Kotlin.coroutines)
}