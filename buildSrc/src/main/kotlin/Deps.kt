@file:Suppress("unused")

object Deps {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${GradleConfig.Plugins.Versions.kotlin}"
    const val annotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotations}"
    const val inject = "javax.inject:javax.inject:${Versions.javaxInject}"

    const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageView}"
    const val circularProgressbar = "com.mikhaellopez:circularprogressbar:${Versions.circularProgressbar}"
    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val lastAdapter = "com.github.nitrico.lastadapter:lastadapter:${Versions.lastAdapter}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val replayingShare = "com.jakewharton.rx2:replaying-share:${Versions.replayingShare}"

    const val nordicsemiScanner = "no.nordicsemi.android.support.v18:scanner:${Versions.nordicsemiScanner}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitAdapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxAndroidBle = "com.polidea.rxandroidble2:rxandroidble:${Versions.rxAndroidBle}"

    const val threeTenApp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"

    const val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    const val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
        const val browser = "androidx.browser:browser:${Versions.browser}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val exifInterface = "androidx.exifinterface:exifinterface:${Versions.exifInterface}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
        const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
        const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleLiveData}"
        const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${GradleConfig.Plugins.Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${GradleConfig.Plugins.Versions.navigation}"
        const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomRxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    }

    object CameraX {
        const val core = "androidx.camera:camera-core:${Versions.camerax}"
        const val camera2 = "androidx.camera:camera-camera2:${Versions.camerax}"
        const val lifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax}"
        const val video = "androidx.camera:camera-video:${Versions.cameraExtensions}"
        const val view = "androidx.camera:camera-view:${Versions.cameraExtensions}"
        const val extensions = "androidx.camera:camera-extensions:${Versions.cameraExtensions}"
        const val mlkitBarcodeScanning = "com.google.mlkit:barcode-scanning:${Versions.mlkitBarcodeScanning}"
        const val mlkitPlayServicesBarcodeScanning = "com.google.android.gms:play-services-mlkit-barcode-scanning:${Versions.mlkitPlayServicesBarcodeScanning}"
    }

    object Google {
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"

        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
        const val mapsUtils = "com.google.maps.android:android-maps-utils:${Versions.mapsUtils}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val playServicesMaps = "com.google.android.gms:play-services-maps:${Versions.maps}"
        const val playServicesLocation = "com.google.android.gms:play-services-location:${Versions.location}"
        const val playServicesAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${GradleConfig.Plugins.Versions.kotlin}"
        const val androidXTruth = "androidx.test.ext:truth:${Versions.androidxTruth}"
        const val androidXjUnit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val room = "androidx.room:room-testing:${Versions.room}"
        const val archCore = "androidx.arch.core:core-testing:${Versions.archComponents}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }
}
