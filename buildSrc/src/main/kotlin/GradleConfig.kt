@file:Suppress("unused")

object GradleConfig {
    object Base {
        const val minSdk = 21
        const val compileSdk = 31
        const val targetSdk = 31
        const val buildTools = "30.0.3"

        const val applicationId = "com.preview"
        const val versionCode = 1
        const val versionName = "1.0"
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val androidLibrary = "com.android.library"
        const val androidExtensions = "kotlin-android-extensions"
        const val kapt = "kotlin-kapt"
        const val googleServices = "com.google.gms.google-services"
        const val safeArgs = "androidx.navigation.safeargs.kotlin"
        const val safeArgsClasspath = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val crashlytics = "com.google.firebase.crashlytics"
        const val benManes = "com.github.ben-manes.versions"

        object Versions {
            const val androidApplication = "7.1.2"
            const val androidLibrary = "7.1.2"
            const val kotlin = "1.6.10"
            const val navigation = "2.4.1"
            const val benManes = "0.42.0"
        }
    }
}
