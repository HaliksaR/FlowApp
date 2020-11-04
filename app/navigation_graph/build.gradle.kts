plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.News.presentation,
        Modules.Features.Quotes.presentation,
        Modules.Features.Quotes.presentation,
        Modules.Features.User.Accounts.presentation,
        Modules.Features.User.SignIn.presentation
    )
    koin()
    navigation()
}