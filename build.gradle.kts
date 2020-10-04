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
        classpath(Libs.Hilt.gradlePlugin)
        classpath(Libs.Koin.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subProjects(Modules.Path.libraries, Modules.Path.feature) {
    project.plugins.whenPluginAdded {
        when (this) {
            is AppPlugin -> applyAppPlugin(project)
            is LibraryPlugin -> applyLibraryPlugin(project, path)
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
    }
    project.dependencies {
        kotlin()
    }
}

fun applyLibraryPlugin(project: Project, path: String) {
    project.configure<BaseExtension> {
        compileSdkVersion(30)
        javaVersionSetups()
        defaultConfig.javaCompileOptions.annotationProcessorOptions {
            arguments + Libs.Androidx.Room.arguments(projectDir)
        }
    }

    project.dependencies {
        kotlin()
    }

    when {
        path.startsWith(Modules.Path.libraries) -> {
            /*config*/
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
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

fun subProjects(vararg folders: String, action: Action<in Project>) {
    folders.forEach { if (project.path == it) return }
    subprojects(action)
}