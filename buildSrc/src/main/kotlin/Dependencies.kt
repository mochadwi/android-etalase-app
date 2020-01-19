import Versions.archRoomVersion
import Versions.butterKnifeVersion
import Versions.chuck
import Versions.constraintLayoutVersion
import Versions.espressoCoreVersion
import Versions.firebaseMessagingVersion
import Versions.glideVersion
import Versions.kotlin
import Versions.kotlinxSerialization
import Versions.multiDexVersion
import Versions.navgraph
import Versions.okHttpVersion
import Versions.paging
import Versions.playService
import Versions.retrofitVersion
import Versions.room
import Versions.supportLibraryVersion
import Versions.workVersion

/**
 * This file contains all the dependencies we need in our project. Because we placed
 * them inside the buildSrc module we get auto code completion inside the rest of the project.
 * More information about this dependency management technique is available at:
 * https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/
 *
 * @author Dennis Wehrle
 */
@Suppress("unused")
object Versions {

    // Android Support
    const val supportLibraryVersion = "27.1.1"
    const val constraintLayoutVersion = "1.0.2"
    const val multiDexVersion = "1.0.1"
    const val archRoomVersion = "1.1.1"
    const val okHttpVersion = "3.10.0"
    const val retrofitVersion = "2.3.0"
    const val glideVersion = "4.7.1"
    const val espressoCoreVersion = "2.2.2"
    const val butterKnifeVersion = "8.8.1"
    const val firebaseMessagingVersion = "17.3.0"

    // Android
    const val applicationId = "io.mochadwi.etalase"
    const val applicationIdFavourite = "io.mochadwi.favourite"
    const val applicationIdWidget = "io.mochadwi.widget"
    const val androidGradle = "3.4.1"
    const val androidBuildTools = "28.0.3"
    const val androidCompileSdk = 28
    const val androidMinSdk = 21
    const val androidTargetSdk = 28

    const val constraintLayout = "1.1.3"
    const val supportLibrary = "1.0.0"
    const val materialDesignLibrary = "1.1.0-alpha05"
    const val multiDex = "2.0.0"
    const val playService = "11.8.0"

    // App Libraries
    const val anko = "0.10.4"
    const val koin = "2.0.0-rc-2"
    const val dagger = "2.15"
    const val glassfishAnnotation = "10.0-b28"
    const val glide = "4.9.0"
    const val gson = "2.8.2"
    const val javaxAnnotation = "1.0"
    const val javaxInject = "1"
    const val kotlin = "1.3.50"
    const val kotlinxSerialization = "0.13.0"
    const val kotlinCoroutine = "1.2.1"
    const val kotlinCoroutineTest = "1.2.1"
    const val leakCanary = "1.5.4"
    const val okHttp = "3.8.1"
    const val retrofit = "2.6.0"
    const val rxAndroid = "2.0.2"
    const val rxJava = "2.1.12"
    const val rxKotlin = "2.2.0"
    const val stetho = "1.5.0"
    const val timber = "4.6.1"
    const val pageIndicatorView = "0.2.0"
    const val moshi = "1.6.0"
    const val lifecycle = "2.1.0-rc01"
    const val arch = "2.1.0-rc01"
    const val room = "2.2.1"
    const val navgraph = "2.1.0-rc01"
    const val paging = "2.1.0"
    const val workVersion = "2.2.0"
    const val dexter = "5.0.0"
    const val imagePickerVersion = "1.13.1"

    // Network Libraries
    const val chuck = "1.1.0"

    // Test Libraries
    const val assertJ = "3.8.0"
    const val dexmakerMockito = "2.16.0"
    const val dexopener = "0.12.1"
    const val espresso = "3.1.0"
    const val jUnit = "4.12"
    const val mockito = "3.0.0"
    const val mockitoKotlin = "1.5.0"
    const val robolectric = "4.3.1"
    const val test = "1.1.0"
    const val testExt = "1.2.0"
}

@Suppress("unused")
object AndroidSupports {
    const val appCompat = "com.android.support:appcompat-v7:${supportLibraryVersion}"
    const val support = "com.android.support:support-v4:${supportLibraryVersion}"
    const val design = "com.android.support:design:${supportLibraryVersion}"
    const val recyclerView = "com.android.support:recyclerview-v7:${supportLibraryVersion}"
    const val cardView = "com.android.support:cardview-v7:${supportLibraryVersion}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${constraintLayoutVersion}"
    const val multiDex = "com.android.support:multidex:${multiDexVersion}"

    const val archRoomRuntime = "android.arch.persistence.room:runtime:${archRoomVersion}"
    const val archRoomComp = "android.arch.persistence.room:compiler:${archRoomVersion}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${okHttpVersion}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${okHttpVersion}"

    const val glide = "com.github.bumptech.glide:glide:${glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${glideVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${retrofitVersion}"
    const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava:${retrofitVersion}"
    const val retrofitRx2 = "com.squareup.retrofit2:adapter-rxjava2:${retrofitVersion}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${retrofitVersion}"
    const val retrofitScalar = "com.squareup.retrofit2:converter-scalars:${retrofitVersion}"

    const val butterKnife = "com.jakewharton:butterknife:${butterKnifeVersion}"
    const val butterKnifeAnnotation = "com.jakewharton:butterknife-compiler:${butterKnifeVersion}"

    const val localization = "com.akexorcist:localizationactivity:1.2.2"
    const val stetho = "com.facebook.stetho:stetho:1.5.0"

    const val fcm = "com.google.firebase:firebase-messaging:${firebaseMessagingVersion}"

    const val junit = "junit:junit:4.12"
    const val junitRunner = "com.android.support.test:runner:1.0.1"
    const val espressoCore = "com.android.support.test.espresso:espresso-core:${espressoCoreVersion}"

}

@Suppress("unused")
object AndroidDependencies {

    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val androidFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.supportLibrary}"
    const val androidSupportAnnotations = "androidx.annotation:annotation:${Versions.supportLibrary}"
    const val androidSupportAppCompatV7 = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
    const val androidSupportConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val androidSupportDesign = "com.google.android.material:material:${Versions.materialDesignLibrary}"
    const val androidSupportRecyclerView = "androidx.recyclerview:recyclerview:${Versions.supportLibrary}"
    const val androidSupportV13 = "androidx.legacy:legacy-support-v13:${Versions.supportLibrary}"
    const val androidSupportCardView = "androidx.cardview:cardview:${Versions.supportLibrary}"
    const val androidSupportMultidex = "androidx.multidex:multidex:${Versions.multiDex}"
    const val androidDatabindingCompiler = "androidx.databinding:databinding-compiler:${Versions.androidGradle}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlin"
    const val kotlin7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"
    const val kotlinxSerializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxSerialization"

    const val coreKtx = "androidx.core:core-ktx:1.0.0"
    const val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val roomCompiler = "androidx.room:room-compiler:$room"
    const val roomRuntime = "androidx.room:room-runtime:$room"
    const val roomRxJava = "androidx.room:room-rxjava2:$room"
    const val roomKtx = "androidx.room:room-ktx:$room"
    const val roomCoroutine = "androidx.room:room-coroutines:$room"

    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:viemodel-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    const val navigationFragment = "androidx.navigation:navigation-fragment:$navgraph"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navgraph"
    const val navigationUi = "androidx.navigation:navigation-ui:$navgraph"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navgraph"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navgraph"

    // alternatively - without Android dependencies for testing
    const val pagingRuntime = "androidx.paging:paging-runtime:$paging"
    const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:$paging"
    const val pagingCommonTest = "androidx.paging:paging-common:$paging"
    const val pagingCommonTestKtx = "androidx.paging:paging-common-ktx:$paging"
    const val playServiceLocation = "com.google.android.gms:play-services-location:$playService"

    // work manager
    const val workManager = "androidx.work:work-runtime:$workVersion"
    const val workManagerKtx = "androidx.work:work-runtime-ktx:$workVersion"
}

@Suppress("unused")
object AppDependencies {

    const val glassfishAnnotation = "org.glassfish:javax.annotation:${Versions.glassfishAnnotation}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotation}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"
    const val leakCanaryDebug = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val leakCanaryRelease = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val pageindicatorview = "com.romandanylyk:pageindicatorview:${Versions.pageIndicatorView}@aar"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitConverterKotlinSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.4.0"
    const val retrofitConverterScalar = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val stetho = "com.facebook.fresco:stetho:${Versions.stetho}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:2.0.0-beta3"
    const val dexter = "com.karumi:dexter:${Versions.dexter}"
    const val imagePicker = "com.github.esafirm.android-image-picker:imagepicker:${Versions.imagePickerVersion}"
    const val anko = "org.jetbrains.anko:anko-commons:${Versions.anko}"
    const val koinCore = "org.koin:koin-core:${Versions.koin}"
    const val koinCoreExt = "org.koin:koin-core-ext:${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val kodaTime = "com.github.debop:koda-time:1.2.1"
    const val jodaTime = "joda-time:joda-time:2.10.2"
    const val icon = "com.joanzapata.iconify:android-iconify-weathericons:2.2.2"
}

@Suppress("unused")
object NetworkDeps {

    const val chuckDebug = "com.readystatesoftware.chuck:library:$chuck"
    const val chuckRelease = "com.readystatesoftware.chuck:library-no-op:$chuck"
}

@Suppress("unused")
object TestDependencies {

    const val fragmentTest = "androidx.fragment:fragment-testing:1.1.0"
    const val testCore = "androidx.test:core:${Versions.testExt}"
    const val testCoreKtx = "androidx.test:core-ktx:${Versions.testExt}"
    const val testRules = "androidx.test:rules:${Versions.test}"
    const val testRunner = "androidx.test:runner:${Versions.test}"
    const val testJunit = "androidx.test.ext:junit:1.1.1"
    const val testTruth = "androidx.test.ext:truth:${Versions.testExt}"
    const val lifecycleTest = "androidx.arch.core:core-testing:${Versions.arch}"
    const val assertj = "org.assertj:assertj-core:${Versions.assertJ}"
    const val dexmakerMockito = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.dexmakerMockito}"
    const val dexopener = "com.github.tmurakami:dexopener:${Versions.dexopener}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val junit = "junit:junit:${Versions.jUnit}"
    const val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlinCoroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutineTest}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val robolectricAnnotations = "org.robolectric:annotations:${Versions.robolectric}"
    const val roomTesting = "androidx.room:room-testing:$room"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
    const val testOrchestrator = "androidx.test:orchestrator:${Versions.test}"
    const val multidexInstrument = "androidx.multidex:multidex-instrumentation:${Versions.multiDex}"
    const val jacocoReport = "com.dicedmelon.gradle:jacoco-android:0.1.4"
}
