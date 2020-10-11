plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
}

dependencies {
    impl(Libs.Androidx.Appcompat)
    impl(Libs.Adapterdelegates4)
    impl(Libs.Androidx.Recyclerview)
    impl(Libs.Androidx.Swiperefreshlayout)
    impl(Libs.Androidx.Coordinatorlayout)
    impl(Libs.Material)
    impl(Libs.Androidx.Lifecycle.lifecycleViewModel)
    coroutines()
}