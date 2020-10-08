plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.User.SignIn.injector,
        Modules.Libraries.fakeNetwork,
        Modules.Libraries.Network
    )
    koin()
}