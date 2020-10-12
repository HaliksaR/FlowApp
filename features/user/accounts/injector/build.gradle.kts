plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(
        Modules.Features.User.Accounts.domain,
        Modules.Features.User.Accounts.presentation,
        Modules.Features.User.Accounts.data
    )
    koin()
}