import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.Gradle.androidPlugin)
        classpath(Libs.Kotlin.gradlePlugin)
        classpath(Libs.Koin.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

subprojects {
    project.tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    project.plugins.whenPluginAdded {
        when (this) {
            is AppPlugin -> applyAppPlugin(path)
            is LibraryPlugin -> applyAndroidLibraryPlugin(path)
            is JavaLibraryPlugin -> applyLibraryPlugin(path)
        }
    }
}

fun Project.applyAppPlugin(path: String) {
    configure<BaseExtension> {
        compileSdkVersion(AppConfigs.compileSdkVersion)
        defaultConfig {
            applicationId = AppConfigs.applicationId
            minSdkVersion(AppConfigs.minSdkVersion)
            targetSdkVersion(AppConfigs.targetSdkVersion)
            versionCode = AppConfigs.versionCode
            versionName = AppConfigs.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        buildFeatures.viewBinding = true
        signingSetups()
        buildTypesSetups()
        javaVersionSetups()
        packagingOptions {
            exclude("META-INF/*.kotlin_module")
        }
    }
    dependencies.kotlin()
}

fun Project.applyAndroidLibraryPlugin(path: String) {
    configure<BaseExtension> {
        compileSdkVersion(30)
        buildFeatures.viewBinding = true
        javaVersionSetups()
    }
    dependencies.kotlin()
}

fun Project.applyLibraryPlugin(path: String) {
    dependencies.kotlin()
}

fun BaseExtension.signingSetups() {
    signingConfigs {
        create(BuildTypes.signingConfigs) {
            keyAlias = AppConfigs.keyAlias
            keyPassword = AppConfigs.keyPass
            storePassword = AppConfigs.keyStore
            storeFile = file("$rootDir/buildSrc/test.jks")
        }
    }
    buildTypes {
        getByName(BuildTypes.release) {
            signingConfig = signingConfigs.findByName(BuildTypes.signingConfigs)
        }
    }
}

fun BaseExtension.buildTypesSetups() {
    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName(BuildTypes.debug) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

fun BaseExtension.javaVersionSetups() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}