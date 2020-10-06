plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.User.SignIn.domain,
        Modules.Features.User.SignIn.presentation,
        Modules.Features.User.SignIn.data
    )
    kotlin()
    koin()
}