plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    gson()
    coroutines()
    retrofit()
}