import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlinx-serialization") version "1.3.30"
    id("mergedJacocoReport")
}

android {
    compileSdkVersion(Versions.androidCompileSdk)
    buildToolsVersion(Versions.androidBuildTools)

    defaultConfig {
        minSdkVersion(Versions.androidMinSdk)
        targetSdkVersion(Versions.androidTargetSdk)
        applicationId = Versions.applicationId
        // TODO: Use semantic versioning, use ribot guideliens
        // ref: https://medium.com/@IlyaEremin/npm-version-for-gradle-android-9137a7dc273c
        versionCode = 1000000
        versionName = "1.0.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // used by Room, to test migrations
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    signingConfigs {
        create("released") {
            storeFile = file("$rootDir/placeholder.jks")
            // TODO: set $APP="yourpassword" on your machine terminal / environment
            storePassword = System.getenv("KEYSTORE_PASS")
            keyAlias = System.getenv("KEYSTORE_PASS")
            keyPassword = System.getenv("KEYSTORE_PASS")
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false
            isZipAlignEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            isTestCoverageEnabled = true
        }
    }

    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
        getByName("test").java.setSrcDirs(listOf("src/test/java", "src/sharedTest/kotlin"))
        getByName("androidTest").java.setSrcDirs(listOf("src/androidTest/java", "src/sharedTest/kotlin"))

        // used by Room, to test migrations
        getByName("androidTest").assets.srcDir(files("$projectDir/schemas"))
    }

    androidExtensions {
        isExperimental = true
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        this as KotlinJvmOptions
        jvmTarget = "1.8"
    }

    flavorDimensions("default")
    productFlavors {
        create("prod") {
            // For release package name
            applicationIdSuffix = ".prod"
            signingConfig = signingConfigs.getByName("released")

            // Env prod server
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            buildConfigField("String", "BASE_IMAGE_URL", "\"https://paper.dropboxstatic.com/static/img/\"")
            buildConfigField("String", "DEFAULT_IMAGE_URL", "\"https://paper.dropboxstatic.com/static/img/favicon/apple-touch-icon.png\"")

            // Inject app name for release
            resValue("string", "app_name", "App")
        }

        create("dev") {
            // For dev package name
            applicationIdSuffix = ".dev"

            // Env dev server
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_IMAGE_URL", "\"https://paper.dropboxstatic.com/static/img/\"")
            buildConfigField("String", "DEFAULT_IMAGE_URL", "\"https://paper.dropboxstatic.com/static/img/favicon/apple-touch-icon.png\"")

            // Inject app name for dev
            resValue("string", "app_name", "Etalase App")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Android Support
    implementation(AndroidDependencies.androidSupportAppCompatV7)
    implementation(AndroidDependencies.androidSupportDesign)
    implementation(AndroidDependencies.androidSupportCardView)
    implementation(AndroidDependencies.androidSupportRecyclerView)
    implementation(AndroidDependencies.androidSupportConstraintLayout)
    implementation(AndroidDependencies.androidSupportAnnotations)
    implementation(AndroidDependencies.playServiceLocation)

    // Android Test
    testImplementation(TestDependencies.kotlinJUnit)
    testImplementation(TestDependencies.kotlinCoroutineTest)
    testImplementation(TestDependencies.mockitoCore)
    // fix this mixing version
    androidTestImplementation(TestDependencies.testJunit)
    androidTestImplementation(TestDependencies.testRules)
    androidTestImplementation(TestDependencies.testRunner)
    androidTestImplementation(TestDependencies.kotlinCoroutineTest)
    androidTestImplementation(TestDependencies.mockitoAndroid)
    androidTestUtil(TestDependencies.testOrchestrator)

    // Kotlin
    implementation(AndroidDependencies.kotlin8)
    implementation(AndroidDependencies.coreKtx)
    implementation(AndroidDependencies.kotlinxSerializationRuntime)

    // Anko
    implementation(AppDependencies.anko)

    // Koin
    implementation(AppDependencies.koinCore)
    implementation(AppDependencies.koinCoreExt)
    implementation(AppDependencies.koinAndroid)
    implementation(AppDependencies.koinViewModel)
    testImplementation(TestDependencies.koinTest)
    androidTestImplementation(TestDependencies.koinTest)

    // ViewModel and LiveData
    implementation(AndroidDependencies.lifecycle)
    kapt(AndroidDependencies.lifecycleCompiler)
    testImplementation(TestDependencies.lifecycleTest)
    androidTestImplementation(TestDependencies.lifecycleTest)

    // Navigation
    implementation(AndroidDependencies.navigationFragment)
    implementation(AndroidDependencies.navigationFragmentKtx)
    implementation(AndroidDependencies.navigationUi)
    implementation(AndroidDependencies.navigationUiKtx)

    // Room
    implementation(AndroidDependencies.roomRuntime)
    implementation(AndroidDependencies.roomKtx)
    kapt(AndroidDependencies.roomCompiler)
    testImplementation(TestDependencies.roomTesting)
    androidTestImplementation(TestDependencies.roomTesting)

    // Networking
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.retrofitConverterKotlinSerialization)
    implementation(AppDependencies.retrofitConverterScalar)
    implementation(AppDependencies.okHttp)
    implementation(AppDependencies.okHttpLogger)
    debugImplementation(NetworkDeps.chuckDebug)
    releaseImplementation(NetworkDeps.chuckRelease)

    // Coroutines
    implementation(AndroidDependencies.kotlinCoroutineCore)
    implementation(AndroidDependencies.kotlinCoroutineAndroid)
    implementation(AndroidDependencies.retrofitCoroutineAdapter)

    // UI
    implementation(AppDependencies.icon)

    // Image
    implementation(AppDependencies.glide)
    kapt(AppDependencies.glideCompiler)
    implementation(AppDependencies.imagePicker)

    // Permission
    implementation(AppDependencies.dexter)

    // Debug
    implementation(AppDependencies.stetho)

    // Time
    implementation(AppDependencies.kodaTime)
    implementation(AppDependencies.jodaTime)
}

// Fix linting error
tasks.withType<KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xuse-experimental=kotlinx.serialization.ImplicitReflectionSerializer",
        "-Xuse-experimental=kotlinx.serialization.UnstableDefault",
        "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xuse-experimental=kotlinx.coroutines.ObsoleteCoroutinesApi"
    )
}