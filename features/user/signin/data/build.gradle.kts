plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    coreModules(
        Modules.Libraries.Core.data
    )
    modules(
        Modules.Features.User.SignIn.domain,
        Modules.Libraries.Network,
        Modules.Libraries.Network.wrappers
    )
    retrofit()
    coroutines()
    gson()
    koin()
}