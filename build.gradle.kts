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

subProjects(
    Modules.Libraries,
    Modules.Features,
    Modules.Features.User,
    Modules.Features.User.SignIn
) {
    project.tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    project.plugins.whenPluginAdded {
        when (this) {
            is AppPlugin -> applyAppPlugin(project)
            is LibraryPlugin -> applyAndroidLibraryPlugin(project, path)
            is JavaLibraryPlugin -> applyLibraryPlugin(project, path)
        }
    }
}

fun applyAppPlugin(project: Project) {
    project.configure<BaseExtension> {
        compileSdkVersion(AppConfigs.compileSdkVersion)
        defaultConfig {
            applicationId = AppConfigs.applicationId
            minSdkVersion(AppConfigs.minSdkVersion)
            targetSdkVersion(AppConfigs.targetSdkVersion)
            versionCode = AppConfigs.versionCode
            versionName = AppConfigs.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        buildTypesSetups()
        javaVersionSetups()
        packagingOptions {
            exclude("META-INF/*.kotlin_module")
        }
    }
    project.dependencies {
        kotlin()
    }
}

fun applyAndroidLibraryPlugin(project: Project, path: String) {
    project.configure<BaseExtension> {
        compileSdkVersion(30)
        javaVersionSetups()
    }

    project.dependencies {
        kotlin()
    }
}

fun applyLibraryPlugin(project: Project, path: String) {
    project.dependencies {
        kotlin()
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

fun subProjects(vararg folders: Any, action: Action<in Project>) {
    folders.forEach { if (project.path == it.toString()) return }
    subprojects(action)
}