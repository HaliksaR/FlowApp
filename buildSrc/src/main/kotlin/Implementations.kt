import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.modules(vararg module: String) {
    module.forEach { impl(project(it)) }
}

fun DependencyHandler.modules(vararg module: Any) {
    module.forEach { impl(project(it.toString())) }
}

fun DependencyHandler.sharedModules(vararg module: Any) {
    module.forEach { impl(project(it.toString())) }
}

fun DependencyHandler.sharedModules(vararg module: String) {
    module.forEach { impl(project(it)) }
}

fun DependencyHandler.coreModules(vararg module: Any) {
    module.forEach { impl(project(it.toString())) }
}

fun DependencyHandler.coreModules(vararg module: String) {
    module.forEach { impl(project(it)) }
}

fun DependencyHandler.kotlin() {
    impl(Libs.Kotlin.stdlib)
}

fun DependencyHandler.retrofit() {
    impl(Libs.Retrofit)
}

fun DependencyHandler.koin() {
    impl(Libs.Koin.core)
    impl(Libs.Koin.coreExt)
    testImpl(Libs.Koin.test)
}

fun DependencyHandler.koinAndroid() {
    impl(Libs.Koin.android)
    impl(Libs.Koin.androidExt)
    impl(Libs.Koin.androidViewModel)
    impl(Libs.Koin.androidScope)
}

fun DependencyHandler.koinAndroidX() {
    impl(Libs.Koin.android)
    impl(Libs.Koin.X.androidExt)
    impl(Libs.Koin.X.androidScope)
    impl(Libs.Koin.X.androidViewModel)
    impl(Libs.Koin.X.androidFragment)
    impl(Libs.Koin.X.androidWorkManager)
    impl(Libs.Koin.X.androidCompose)
}

fun DependencyHandler.coroutinesAndroid() {
    impl(Libs.Kotlin.coroutinesAndroid)
}

fun DependencyHandler.coroutines() {
    impl(Libs.Kotlin.coroutines)
}

fun DependencyHandler.okhttp() {
    impl(Libs.Okhttp3)
}

fun DependencyHandler.okhttpMockWebServer() {
    testImpl(Libs.Okhttp3.mockWebServer)
}

fun DependencyHandler.moshi() {
    impl(Libs.Moshi)
}

fun DependencyHandler.moshiForRetrofit() {
    impl(Libs.Moshi)
    impl(Libs.Moshi.kt)
    impl(Libs.Moshi.converter)
    impl(Libs.Moshi.adapter)
}

fun DependencyHandler.room() {
    impl(Libs.Androidx.Room.runtime)
    kapt(Libs.Androidx.Room.compiler)
    impl(Libs.Androidx.Room.ktx)
    testImpl(Libs.Androidx.Room.testing)
}

fun DependencyHandler.gson() {
    impl(Libs.Gson)
}

fun DependencyHandler.gsonRetrofit() {
    impl(Libs.Gson)
    impl(Libs.Gson.converter)
}

fun DependencyHandler.rxKotlin() {
    impl(Libs.RxKotlin)
}

fun DependencyHandler.rxRetrofit() {
    impl(Libs.RxJava.adapter)
}

fun DependencyHandler.rxAndroid() {
    impl(Libs.RxAndroid)
}

fun DependencyHandler.fragment() {
    impl(Libs.Androidx.Fragment.ktx)
    impl(Libs.Androidx.Fragment)
    impl(Libs.Androidx.Fragment.testing)
}

fun DependencyHandler.navigation() {
    impl(Libs.Androidx.Navigation.uiKtx)
    impl(Libs.Androidx.Navigation.fragmentKtx)
    androidTestImpl(Libs.Androidx.Navigation.testing)
}