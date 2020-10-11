plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.User.SignIn.injector,
        Modules.Features.News.injector,
        Modules.Features.Quotes.injector,
        Modules.Libraries.Core.injector,
        Modules.Libraries.fakeNetwork,
        Modules.Libraries.Network,
        Modules.App.navigation
    )
    koin()
}