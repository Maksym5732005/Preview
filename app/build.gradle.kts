plugins {
    id(GradleConfig.Plugins.androidApplication)
    id(GradleConfig.Plugins.kotlinAndroid)
}

android {
    compileSdk = GradleConfig.Base.compileSdk

    defaultConfig {
        applicationId = GradleConfig.Base.applicationId
        minSdk = GradleConfig.Base.minSdk
        targetSdk = GradleConfig.Base.targetSdk
        versionCode = GradleConfig.Base.versionCode
        versionName = GradleConfig.Base.versionName

        testInstrumentationRunner = GradleConfig.Base.testRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.core)

    implementation(Deps.Google.material)

    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.Test.androidXjUnit)
    androidTestImplementation(Deps.Test.espresso)
}