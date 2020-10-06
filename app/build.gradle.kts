plugins {
    id(Plugins.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    modules(
        Modules.Libraries.Core,
        Modules.Libraries.Network,
        Modules.Libraries.fakeNetwork,
        Modules.Libraries.retroFlow,
        Modules.Features.User.SignIn.injector
    )
    impl(Libs.Androidx.Core.ktx)
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Androidx.Constraintlayout)
    impl(Libs.Androidx.Navigation.uiKtx)
    impl(Libs.Androidx.Navigation.fragmentKtx)
    koin()
    koinAndroidX()
}