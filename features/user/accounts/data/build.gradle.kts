plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    modules(Modules.Features.User.Accounts.domain)
    room()
    coroutines()
    koin()
    koinAndroidX()
}