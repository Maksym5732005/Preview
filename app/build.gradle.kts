plugins {
    id(GradleConfig.Plugins.androidApplication)
    id(GradleConfig.Plugins.kotlinAndroid)
    id(GradleConfig.Plugins.safeArgs)
    id(GradleConfig.Plugins.kapt)
}

kapt {
    correctErrorTypes = true
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

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.lifeCycleLiveData)
    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)
    implementation(Deps.AndroidX.swipe)

    implementation(Deps.Google.material)

    implementation(Deps.epoxy)
    kapt(Deps.epoxyProcessor)

    implementation (Deps.glide)
    kapt (Deps.glideCompiler)

    kapt (Deps.Google.daggerCompiler)
    implementation (Deps.Google.dagger)
    implementation (Deps.Google.daggerAndroid)
    implementation (Deps.Google.daggerAndroidSupport)
    kapt (Deps.Google.daggerAndroidProcessor)

    implementation (Deps.Google.gson)

    implementation (Deps.retrofit)
    implementation (Deps.retrofitConverterGson)
    implementation (Deps.retrofitAdapterRxJava2)
    implementation (Deps.okHttp)
    implementation (Deps.okHttpLoggingInterceptor)

    implementation (Deps.rxJava)
    implementation (Deps.rxKotlin)
    implementation (Deps.rxAndroid)

    implementation(Deps.timber)
    implementation(Deps.jodaTime)

    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.Test.androidXjUnit)
    androidTestImplementation(Deps.Test.espresso)
}