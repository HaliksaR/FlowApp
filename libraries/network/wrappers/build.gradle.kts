plugins {
    id(Plugins.Kotlin.library)
    id(Plugins.Kotlin.toString())
}

dependencies {
    kotlin()
    gson()
    coroutines()
    retrofit()
}