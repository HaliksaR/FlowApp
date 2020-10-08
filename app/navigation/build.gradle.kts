plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    modules(Modules.Features.User.SignIn.presentation)
    impl(Libs.Androidx.Navigation.uiKtx)
    impl(Libs.Androidx.Navigation.fragmentKtx)
}